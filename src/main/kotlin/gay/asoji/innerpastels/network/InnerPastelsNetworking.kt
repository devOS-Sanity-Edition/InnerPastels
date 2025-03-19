package gay.asoji.innerpastels.network

import gay.asoji.innerpastels.InnerPastels
import gay.asoji.innerpastels.network.clientbound.ClientboundClearPlayerCapePacket
import gay.asoji.innerpastels.network.clientbound.ClientboundSetPlayerCapePacket
import gay.asoji.innerpastels.network.clientbound.InnerPastelsClientboundPacket
import gay.asoji.innerpastels.network.serverbound.InnerPastelsServerboundPacket
import gay.asoji.innerpastels.network.serverbound.ServerboundClearCapeStylePacket
import gay.asoji.innerpastels.network.serverbound.ServerboundSetCapeStylePacket
import net.fabricmc.api.EnvType
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation

object InnerPastelsNetworking {
    // Serverbound (Client -> Server) Packets
    val SET_CAPE_STYLE = registerServerbound(ResourceLocation.fromNamespaceAndPath(InnerPastels.MOD_ID, "set_cape_style"), ServerboundSetCapeStylePacket.STREAM_CODEC)
    val CLEAR_CAPE_STYLE = registerServerbound(ResourceLocation.fromNamespaceAndPath(InnerPastels.MOD_ID, "clear_cape_style"), ServerboundClearCapeStylePacket.STREAM_CODEC)

    // Clientbound (Server -> Client) Packets
    val SET_PLAYER_CAPE = registerClientbound(ResourceLocation.fromNamespaceAndPath(InnerPastels.MOD_ID, "set_player_cape"), ClientboundSetPlayerCapePacket.STREAM_CODEC)
    val CLEAR_PLAYER_CAPE = registerClientbound(ResourceLocation.fromNamespaceAndPath(InnerPastels.MOD_ID, "clear_player_cape"), ClientboundClearPlayerCapePacket.STREAM_CODEC)

    fun init() {
    }

    fun <T : InnerPastelsServerboundPacket> registerServerbound(id: ResourceLocation, codec: StreamCodec<in RegistryFriendlyByteBuf, T>): CustomPacketPayload.Type<T> {
        val type = CustomPacketPayload.Type<T>(id)
        PayloadTypeRegistry.playC2S().register(type, codec)

        ServerPlayNetworking.registerGlobalReceiver(type, InnerPastelsServerboundPacket::handleServer)

        return type
    }

    fun <T : InnerPastelsClientboundPacket> registerClientbound(id: ResourceLocation, codec: StreamCodec<in RegistryFriendlyByteBuf, T>): CustomPacketPayload.Type<T> {
        val type = CustomPacketPayload.Type<T>(id)
        PayloadTypeRegistry.playS2C().register(type, codec)

        if (FabricLoader.getInstance().environmentType == EnvType.CLIENT) {
            registerClientHandler(type)
        }

        return type
    }

    // Separated into a different method, otherwise KnotClassLoader panics on the dedicated server
    private fun <T : InnerPastelsClientboundPacket> registerClientHandler(type: CustomPacketPayload.Type<T>) {
        ClientPlayNetworking.registerGlobalReceiver(type, InnerPastelsClientboundPacket::handleClient)
    }
}