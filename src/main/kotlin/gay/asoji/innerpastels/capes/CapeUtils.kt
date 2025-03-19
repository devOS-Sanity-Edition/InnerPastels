package gay.asoji.innerpastels.capes

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.JsonOps
import com.mojang.serialization.codecs.RecordCodecBuilder
import gay.asoji.innerpastels.InnerPastels
import gay.asoji.innerpastels.config.Config
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.StringRepresentable
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*
import java.util.concurrent.CompletableFuture
import kotlin.jvm.optionals.getOrDefault

enum class CapeUtils {
    INSTANCE;

    private val registeredDevs: Multimap<UUID, CapeStyle> = HashMultimap.create()

    private var initialized = false

    fun init() {
        if (initialized) return
        initialized = true
        refresh()
    }

    private fun refresh() {
        CompletableFuture.runAsync {
            val client = HttpClient.newHttpClient()
            val request = HttpRequest.newBuilder(URI.create(URL))
                .GET()
                .build()
            try {
                val body = client.send(request, HttpResponse.BodyHandlers.ofString()).body()
                val users = User.LIST_CODEC.decode(JsonOps.INSTANCE, Gson().fromJson(body, JsonElement::class.java)).result()
                    .getOrDefault(Pair(emptyList(), null)).first

                registeredDevs.clear()
                users.forEach { u ->
                    u.capes.forEach { c ->
                        registeredDevs.put(UUID.fromString(u.uuid), c)
                    }
                }
            } catch (e: Exception) {
                InnerPastels.LOGGER.error("Failed to fetch cape data", e)
            }
        }
    }

    fun getDevCape(id: UUID): CapeStyle? {
        if (registeredDevs.containsKey(id)) {
            val capes = registeredDevs.get(id)
            capes.forEach { s ->
                if (Config.INSTANCE.get().capeStyle == s)
                    return s
            }
            
            return capes.first()
        }

        return null
    }

    companion object {
        private const val URL = "https://raw.githubusercontent.com/asoji/CapeData/refs/heads/main/capes.json"
    }

    data class User(
        val username: String,
        val uuid: String,
        val reason: String,
        val capes: List<CapeStyle>
    ) {
        companion object {
            private val CODEC: Codec<User> = RecordCodecBuilder.create { instance ->
                instance.group(
                    Codec.STRING.fieldOf("username").forGetter { i -> i.username },
                    Codec.STRING.fieldOf("uuid").forGetter { i -> i.uuid },
                    Codec.STRING.fieldOf("reason").forGetter { i -> i.reason },
                    CapeStyle.CODEC.listOf().fieldOf("capes").forGetter { i -> i.capes.toList() },
                ).apply(instance) { username, uuid, reason, capes -> User(username, uuid, reason, capes) }
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
        }
        
        val location: ResourceLocation =
            ResourceLocation.fromNamespaceAndPath(InnerPastels.MOD_ID, "textures/misc/${serializedName}.png")

        override fun getSerializedName(): String {
            return name.lowercase(Locale.ROOT)
        }
    }
}
