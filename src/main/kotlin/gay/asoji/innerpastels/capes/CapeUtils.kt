package gay.asoji.innerpastels.capes

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.JsonOps
import com.mojang.serialization.codecs.RecordCodecBuilder
import gay.asoji.innerpastels.InnerPastels
import gay.asoji.innerpastels.config.Config
import gay.asoji.innerpastels.network.clientbound.ClientboundClearPlayerCapePacket
import gay.asoji.innerpastels.network.clientbound.ClientboundSetPlayerCapePacket
import gay.asoji.innerpastels.network.serverbound.ServerboundSetCapeStylePacket
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.commands.SharedSuggestionProvider
import net.minecraft.network.chat.Component
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*
import java.util.concurrent.CompletableFuture
import kotlin.jvm.optionals.getOrDefault

object CapeUtils {
    private const val URL = "https://raw.githubusercontent.com/asoji/CapeData/refs/heads/main/capes.json"
    private val registeredDevs: Multimap<UUID, CapeStyle> = HashMultimap.create()
    val selectedCapeStyle = mutableMapOf<UUID, CapeStyle>()

    init {
        refresh()

        ServerPlayConnectionEvents.JOIN.register { handler, sender, server ->
            selectedCapeStyle.forEach { (uuid, style) ->
                if (!registeredDevs.containsEntry(uuid, style))
                    return@forEach

                ServerPlayNetworking.send(handler.player, ClientboundSetPlayerCapePacket(uuid, style))
            }
        }
    }

    fun initClient() {
        ClientPlayConnectionEvents.JOIN.register { handler, sender, client ->
            if (registeredDevs.containsEntry(handler.id, Config.get().capeStyle)) {
                ClientPlayNetworking.send(ServerboundSetCapeStylePacket(Config.get().capeStyle!!))
            }
        }

        ClientCommandRegistrationCallback.EVENT.register { dispatcher, ctx ->
            dispatcher.register(
                ClientCommandManager.literal("innerpastels")
                    .then(
                        ClientCommandManager.literal("devcape")
                            .then(
                                ClientCommandManager.argument("style", StringArgumentType.word())
                                    .suggests { ctx, it ->
                                        SharedSuggestionProvider.suggest(CapeStyle.entries.filter { s -> registeredDevs.containsEntry(ctx.source.player.uuid, s) }.map { s -> s.serializedName }.toMutableList()
                                            .apply {
                                                this.add("none")
                                            }, it)
                                    }
                                    .executes { ctx ->
                                        val capeStyleText = StringArgumentType.getString(ctx, "style")

                                        if (capeStyleText == "none") {
                                            removeSelectedCape(ctx.source.player.uuid)
                                            ctx.source.sendFeedback(Component.literal("Cleared your dev cape!"))
                                            Config.get().capeStyle = null
                                            Config.save()
                                        }

                                        val capeStyle = CapeStyle.entries.firstOrNull { it.serializedName == capeStyleText }

                                        if (capeStyle == null) {
                                            ctx.source.sendError(Component.literal("Invalid cape style!"))
                                            return@executes 0
                                        }

                                        if (!registeredDevs.containsEntry(ctx.source.player.uuid, capeStyle)) {
                                            ctx.source.sendError(Component.literal("You do not have this cape!"))
                                            return@executes 0
                                        }

                                        trySetSelectedCape(ctx.source.player.uuid, capeStyle)
                                        ctx.source.sendFeedback(Component.literal("Set your dev cape to $capeStyle!"))

                                        Config.get().capeStyle = capeStyle
                                        Config.save()

                                        1
                                    }
                            )
                    )
            )
        }
    }

    fun trySetSelectedCape(uuid: UUID, style: CapeStyle, server: MinecraftServer? = null) {
        if (registeredDevs.containsEntry(uuid, style)) {
            selectedCapeStyle[uuid] = style

            if (server != null) {
                server.playerList.players.forEach { player ->
                    ServerPlayNetworking.send(player, ClientboundSetPlayerCapePacket(uuid, style))
                }
            } else {
                sendCapeStyleToServer(style)
            }
        }
    }

    fun removeSelectedCape(uuid: UUID, server: MinecraftServer? = null) {
        selectedCapeStyle.remove(uuid)

        server?.playerList?.players?.forEach { player ->
            ServerPlayNetworking.send(player, ClientboundClearPlayerCapePacket(uuid))
        }
    }

    // Separated into a different function because otherwise KnotClassLoader panics
    private fun sendCapeStyleToServer(style: CapeStyle) {
        ClientPlayNetworking.send(ServerboundSetCapeStylePacket(style))
    }

    private fun refresh() {
        CompletableFuture.runAsync {
            val client = HttpClient.newHttpClient()
            val request = HttpRequest.newBuilder(URI.create(URL))
                .GET()
                .build()
            try {
                val body = client.send(request, HttpResponse.BodyHandlers.ofString()).body()
                val users = User.LIST_CODEC.decode(JsonOps.INSTANCE, Gson().fromJson(body, JsonElement::class.java))
                    .resultOrPartial { InnerPastels.LOGGER.error(it) }
                    .getOrDefault(Pair(emptyList(), null)).first

                registeredDevs.clear()
                users.forEach { u ->
                    u.capes.forEach { c ->
                        registeredDevs.put(UUID.fromString(u.uuid), c)
                    }
                }

                InnerPastels.LOGGER.info("Loaded dev cape data.")
            } catch (e: Exception) {
                InnerPastels.LOGGER.error("Failed to fetch cape data", e)
            }
        }
    }

    fun getDevCape(id: UUID): CapeStyle? {
        if (registeredDevs.containsKey(id)) {
            return selectedCapeStyle[id]
        }

        return null
    }

    data class User(
        val username: String,
        val uuid: String,
        val reason: String,
        val capes: List<CapeStyle>
    ) {
        companion object {
            private val CODEC: Codec<User> = RecordCodecBuilder.create { it.group(
                    Codec.STRING.fieldOf("username").forGetter(User::username),
                    Codec.STRING.fieldOf("uuid").forGetter(User::uuid),
                    Codec.STRING.fieldOf("reason").forGetter(User::reason),
                    CapeStyle.CODEC.listOf().fieldOf("capes").forGetter { i -> i.capes.toList() },
                ).apply(it, ::User)
            }

            val LIST_CODEC: Codec<List<User>> = CODEC.listOf()
        }
    }
    
    enum class CapeStyle : StringRepresentable {
        INNER,
        SOFTER,
        DESOLATED;
        
        companion object {
            val CODEC: Codec<CapeStyle> = StringRepresentable.fromEnum(CapeStyle::values)
            val STREAM_CODEC = ByteBufCodecs.idMapper(ByIdMap.continuous(CapeStyle::ordinal, entries.toTypedArray(), ByIdMap.OutOfBoundsStrategy.CLAMP), CapeStyle::ordinal)
        }
        
        val location: ResourceLocation =
            ResourceLocation.fromNamespaceAndPath(InnerPastels.MOD_ID, "textures/misc/${serializedName}.png")

        override fun getSerializedName(): String {
            return name.lowercase(Locale.ROOT)
        }
    }
}
