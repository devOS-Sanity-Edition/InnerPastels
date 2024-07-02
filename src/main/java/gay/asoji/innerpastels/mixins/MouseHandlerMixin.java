package gay.asoji.innerpastels.mixins;

import gay.asoji.innerpastels.client.imgui.InnerPastelsImGuiImpl;
import gay.asoji.innerpastels.events.InputAction;
import gay.asoji.innerpastels.events.MouseInputEvent;
import gay.asoji.innerpastels.events.MouseScrollInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "onPress", at = @At("HEAD"))
    private void onPress(long handle, int button, int action, int mods, CallbackInfo ci) {
        if (handle != minecraft.getWindow().getWindow())
            return;

        InputAction inputAction = InputAction.from(action);
        MouseInputEvent.EVENT.invoker().onMouseInput(button, inputAction, mods);
    }

    @Inject(method = "onScroll", at = @At("HEAD"))
    private void onScroll(long windowPointer, double xOffset, double yOffset, CallbackInfo ci) {
        if (windowPointer != minecraft.getWindow().getWindow()) {
            return;
        }

        MouseScrollInputEvent.EVENT.invoker().onScroll(xOffset, yOffset);
    }

    @Inject(method = "grabMouse", at = @At("HEAD"))
    public void grabMouse(CallbackInfo ci) {
        InnerPastelsImGuiImpl.INSTANCE.mouseFocus();
    }

    @Inject(method = "xpos", at = @At("HEAD"), cancellable = true)
    public void cancelMouseX(CallbackInfoReturnable<Double> cir) {
        if (InnerPastelsImGuiImpl.INSTANCE.isMouseHidden()) {
            cir.setReturnValue(Double.MIN_VALUE);
        }
    }

    @Inject(method = "ypos", at = @At("HEAD"), cancellable = true)
    public void cancelMouseY(CallbackInfoReturnable<Double> cir) {
        if (InnerPastelsImGuiImpl.INSTANCE.isMouseHidden()) {
            cir.setReturnValue(Double.MIN_VALUE);
        }
    }
}
