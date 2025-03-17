package gay.asoji.innerpastels.mixins.dearimgui;

import com.mojang.blaze3d.platform.DisplayData;
import com.mojang.blaze3d.platform.ScreenManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.platform.WindowEventHandler;
import com.moulberry.mixinconstraints.annotations.IfDevEnvironment;
import gay.asoji.innerpastels.client.imgui.InnerPastelsImGuiImpl;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@IfDevEnvironment
@Mixin(Window.class)
public class GLFWInitMixin {
    @Shadow
    @Final
    private long window;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void onGLFWInit(WindowEventHandler eventHandler, ScreenManager screenManager, DisplayData displayData, String preferredFullscreenVideoMode, String title, CallbackInfo ci) {
        InnerPastelsImGuiImpl.INSTANCE.initialize(window);
    }
}
