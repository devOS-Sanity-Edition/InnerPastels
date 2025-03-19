package gay.asoji.innerpastels.network.serverbound

import gay.asoji.innerpastels.capes.CapeUtils
import gay.asoji.innerpastels.network.InnerPastelsNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload

object ServerboundClearCapeStylePacket : InnerPastelsServerboundPacket {
    val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, ServerboundClearCapeStylePacket> = StreamCodec.unit(ServerboundClearCapeStylePacket)

    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> {
        return InnerPastelsNetworking.CLEAR_CAPE_STYLE
    }

    override fun handleServer(ctx: ServerPlayNetworking.Context) {
        CapeUtils.removeSelectedCape(ctx.player().uuid, ctx.server())
    }
}