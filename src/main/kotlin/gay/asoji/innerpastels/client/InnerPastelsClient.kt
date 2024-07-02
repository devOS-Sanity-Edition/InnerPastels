package gay.asoji.innerpastels.client

import com.mojang.blaze3d.platform.InputConstants
import gay.asoji.innerpastels.client.imgui.ImGuiPanel
import gay.asoji.innerpastels.client.imgui.InnerPastelsImGuiImpl
import gay.asoji.innerpastels.client.imgui.InnerPastelsImGuiImpl.endFrame
import gay.asoji.innerpastels.client.imgui.InnerPastelsImGuiImpl.initialize
import gay.asoji.innerpastels.client.imgui.InnerPastelsImGuiImpl.startFrame
import gay.asoji.innerpastels.events.InputAction
import gay.asoji.innerpastels.events.KeyInputEvent
import gay.asoji.innerpastels.events.MouseInputEvent
import gay.asoji.innerpastels.events.MouseScrollInputEvent
import imgui.type.ImBoolean
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.KeyMapping
import net.minecraft.client.Minecraft
import org.lwjgl.glfw.GLFW

class InnerPastelsClient : ClientModInitializer {
    private var isImGuiRenderEnabled: Boolean = false

    val DEVELOPER_UI_BINDING = KeyMapping(
        "key.innerpastels.developerui",
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_M,
        "category.innerpastels.developer"
    )

    fun initializeDevKeybinds() {
        val toggleUIKeybind = KeyBindingHelper.registerKeyBinding(DEVELOPER_UI_BINDING)

        ClientTickEvents.END_CLIENT_TICK.register(::onTickDeveloperScreen)
    }

    fun onTickDeveloperScreen(client: Minecraft) {
        while (DEVELOPER_UI_BINDING.consumeClick()) {
            if (client.player != null && client.screen == null) {
                isImGuiRenderEnabled = !isImGuiRenderEnabled
            }
        }
    }

    override fun onInitializeClient() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment) {
            initializeDevKeybinds()
        }

        HudRenderCallback.EVENT.register { gui, tickDelta ->
            initialize(Minecraft.getInstance().window.window)
            startFrame()

            if (isImGuiRenderEnabled) {
                panels.forEach {
                    it.render(ImBoolean())
                }
            }

            endFrame()
        }

        KeyInputEvent.EVENT.register { key, action, mods ->
            when (action ?: return@register) {
                InputAction.PRESS -> InnerPastelsImGuiImpl.keyPress(key, mods)
                InputAction.RELEASE -> InnerPastelsImGuiImpl.keyRelease(key, mods)
            }
        }

        MouseInputEvent.EVENT.register { button, action, mods ->
            val mouseX = Minecraft.getInstance().mouseHandler.xpos()
            val mouseY = Minecraft.getInstance().mouseHandler.ypos()
            when (action ?: return@register) {
                InputAction.PRESS -> InnerPastelsImGuiImpl.mouseClick(mouseX, mouseY, button)
                InputAction.RELEASE -> InnerPastelsImGuiImpl.mouseRelease(mouseX, mouseY, button)
            }
        }

        MouseScrollInputEvent.EVENT.register { xOffset, yOffset ->
            InnerPastelsImGuiImpl.mouseScroll(xOffset, yOffset)
        }
    }

    companion object {
        val panels = mutableListOf<ImGuiPanel>()
    }
}