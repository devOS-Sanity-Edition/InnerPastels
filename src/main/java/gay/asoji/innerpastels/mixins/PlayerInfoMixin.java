package gay.asoji.innerpastels.mixins;

import com.mojang.authlib.GameProfile;
import gay.asoji.innerpastels.InnerPastels;
import gay.asoji.innerpastels.capes.CapeUtils;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import java.util.function.Supplier;

@Mixin(value = PlayerInfo.class, priority = 1100)
public class PlayerInfoMixin {
    @Unique
    private static final ResourceLocation DEV_CAPE = ResourceLocation.tryBuild(InnerPastels.MOD_ID, "textures/misc/cape.png");
    @Shadow
    @Final
    private GameProfile profile;
    @Mutable
    @Shadow
    @Final
    private Supplier<PlayerSkin> skinLookup;
    @Unique
    private boolean innerpastels$texturesLoaded;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void replaceSkinInfoIfNeeded(GameProfile gameProfile, boolean bl, CallbackInfo ci) {
        if (!innerpastels$texturesLoaded && CapeUtils.INSTANCE.useDevCape(profile.getId())) {
            innerpastels$texturesLoaded = true;
            var original = this.skinLookup;
            this.skinLookup = () -> {
                var originalResult = original.get();
                return new PlayerSkin(originalResult.texture(), originalResult.textureUrl(), DEV_CAPE, originalResult.elytraTexture(), originalResult.model(), originalResult.secure());
            };
        }
    }

    @Inject(method = "getSkin", at = @At("RETURN"), cancellable = true)
    private void replaceSkinCapeIfNeeded(CallbackInfoReturnable<PlayerSkin> cir) {
        var skin = cir.getReturnValue();

        if (Objects.equals(DEV_CAPE, skin.capeTexture()) && !CapeUtils.INSTANCE.useDevCape(profile.getId())) {
            var playerSkin = new PlayerSkin(skin.texture(), skin.textureUrl(), null, skin.elytraTexture(), skin.model(), skin.secure());

            this.skinLookup = () -> playerSkin;
            cir.setReturnValue(playerSkin);
        }
    }
}
