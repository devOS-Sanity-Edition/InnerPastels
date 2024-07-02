package gay.asoji.innerpastels.misc

import gay.asoji.fmw.FMW
import gay.asoji.innerpastels.InnerPastels
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.Minecraft

/**
 * Development Environment HUD Overlay with a red disclaimer on the top center of the screen, and dependent mods on the bottom left of your screen
 *
 * Call `DevDisclaimer.init()` in your Client class' `onInitializeClient` function, and `InnerPastels.registerMods(MOD_ID)` in your Main class' `onInitialize` function to add to the list
 */
@Environment(EnvType.CLIENT)
object DevDisclaimer {
    fun init() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment) {
            HudRenderCallback.EVENT.register { drawContext, tickDelta ->
                val client = Minecraft.getInstance()

                if (client.gui.debugOverlay.showDebugScreen()) return@register

                for ((i, mod) in InnerPastels.otherPastelMods.withIndex()) {
                    drawContext.drawString(client.font, "${FMW.getName(mod)} v${FMW.getVersionString(mod)}", 2, (client.window.guiScaledHeight - 36 - (i * 12)), 0xFFFFFF, true)
                }
                drawContext.drawString(client.font, "Inner Pastels v${FMW.getVersionString(InnerPastels.MOD_ID)}", 2, (client.window.guiScaledHeight - 24), 0xFFFFFF, true)
                drawContext.drawCenteredString(client.font, "What you see here may not be representative of the final build!", (client.window.guiScaledWidth / 2), 32, 0xffb3c6)
            }
        }
    }
}