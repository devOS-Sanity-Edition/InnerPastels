package gay.asoji.innerpastels.network.clientbound

import gay.asoji.innerpastels.capes.CapeUtils
import gay.asoji.innerpastels.network.InnerPastelsNetworking
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.core.UUIDUtil
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import java.util.*

data class ClientboundSetPlayerCapePacket(
    val uuid: UUID,
    val capeStyle: CapeUtils.CapeStyle
) : InnerPastelsClientboundPacket {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> {
        return InnerPastelsNetworking.SET_PLAYER_CAPE
    }

    @Environment(EnvType.CLIENT)
    override fun handleClient(ctx: ClientPlayNetworking.Context) {
        CapeUtils.selectedCapeStyle[this.uuid] = capeStyle
    }

    companion object {
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, ClientboundSetPlayerCapePacket> = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, ClientboundSetPlayerCapePacket::uuid,
            CapeUtils.CapeStyle.STREAM_CODEC, ClientboundSetPlayerCapePacket::capeStyle,
            ::ClientboundSetPlayerCapePacket
        )
    }
}
