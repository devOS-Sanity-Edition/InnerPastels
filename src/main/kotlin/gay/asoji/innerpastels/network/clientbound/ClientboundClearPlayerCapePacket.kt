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

data class ClientboundClearPlayerCapePacket(
    val uuid: UUID
) : InnerPastelsClientboundPacket {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> {
        return InnerPastelsNetworking.CLEAR_PLAYER_CAPE
    }

    @Environment(EnvType.CLIENT)
    override fun handleClient(ctx: ClientPlayNetworking.Context) {
        CapeUtils.removeSelectedCape(this.uuid)
    }

    companion object {
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, ClientboundClearPlayerCapePacket> = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, ClientboundClearPlayerCapePacket::uuid,
            ::ClientboundClearPlayerCapePacket
        )
    }
}
