package gay.asoji.innerpastels.mixins;

import gay.asoji.innerpastels.events.InputAction;
import gay.asoji.innerpastels.events.KeyInputEvent;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public class KeyboardHandlerMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "keyPress", at = @At("HEAD"))
    private void onKeyPress(long handle, int key, int scancode, int action, int mods, CallbackInfo ci) {
        if (handle != minecraft.getWindow().getWindow())
            return;

        InputAction inputAction = InputAction.from(action);
        KeyInputEvent.EVENT.invoker().onKeyInput(key, inputAction, mods);
    }


}
