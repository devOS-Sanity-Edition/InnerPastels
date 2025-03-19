package gay.asoji.innerpastels.network.clientbound

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.network.protocol.common.custom.CustomPacketPayload

interface InnerPastelsClientboundPacket : CustomPacketPayload {
    @Environment(EnvType.CLIENT)
    fun handleClient(ctx: ClientPlayNetworking.Context)
}