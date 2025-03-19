package gay.asoji.innerpastels.network.serverbound

import gay.asoji.innerpastels.capes.CapeUtils
import gay.asoji.innerpastels.network.InnerPastelsNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload

data class ServerboundSetCapeStylePacket(
    val capeStyle: CapeUtils.CapeStyle
) : InnerPastelsServerboundPacket {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> {
        return InnerPastelsNetworking.SET_CAPE_STYLE
    }

    override fun handleServer(ctx: ServerPlayNetworking.Context) {
        CapeUtils.trySetSelectedCape(ctx.player().uuid, this.capeStyle, ctx.server())
    }

    companion object {
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, ServerboundSetCapeStylePacket> = StreamCodec.composite(
            CapeUtils.CapeStyle.STREAM_CODEC, ServerboundSetCapeStylePacket::capeStyle,
            ::ServerboundSetCapeStylePacket
        )
    }
}