package gay.asoji.innerpastels.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import gay.asoji.innerpastels.client.ImGuiClient;
import gay.asoji.innerpastels.client.imgui.InnerPastelsImGuiImpl;
import imgui.type.ImBoolean;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSystem.class)
public class RenderSystemMixin {
    @Inject(method = "flipFrame", at = @At("HEAD"), remap = false)
    private static void imgui$renderImGui(long handle, CallbackInfo ci) {
        InnerPastelsImGuiImpl.INSTANCE.initialize(handle);
        InnerPastelsImGuiImpl.INSTANCE.startFrame();

        if (ImGuiClient.INSTANCE.isImGuiRenderEnabled()) {
            ImGuiClient.INSTANCE.getPanels().forEach((it) -> {
                        it.theme();
                        it.render(new ImBoolean());
                    }
            );
        }
        
        InnerPastelsImGuiImpl.INSTANCE.endFrame();
    }
}
