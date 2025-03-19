package gay.asoji.innerpastels.network.serverbound

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.network.protocol.common.custom.CustomPacketPayload

interface InnerPastelsServerboundPacket : CustomPacketPayload {
    fun handleServer(ctx: ServerPlayNetworking.Context)
}