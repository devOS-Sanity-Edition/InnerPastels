package gay.asoji.innerpastels

import gay.asoji.innerpastels.capes.CapeUtils
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment

@Environment(EnvType.CLIENT)
class InnerPastelsClient : ClientModInitializer {
    override fun onInitializeClient() {
        CapeUtils.INSTANCE.init()
    }
}