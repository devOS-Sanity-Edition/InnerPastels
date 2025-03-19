package gay.asoji.innerpastels.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.authlib.GameProfile;
import gay.asoji.innerpastels.capes.CapeUtils;
import gay.asoji.innerpastels.capes.CapeUtils.CapeStyle;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.resources.PlayerSkin;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.function.Supplier;

@Mixin(value = PlayerInfo.class, priority = 1100)
public class PlayerInfoMixin {
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
        var cape = CapeUtils.INSTANCE.getDevCape(profile.getId());
        if (!innerpastels$texturesLoaded && cape != null) {
            innerpastels$texturesLoaded = true;
            var original = this.skinLookup;
            this.skinLookup = () -> {
                var originalResult = original.get();
                return new PlayerSkin(originalResult.texture(), originalResult.textureUrl(), cape.getLocation(), originalResult.elytraTexture(), originalResult.model(), originalResult.secure());
            };
        }
    }

    @ModifyReturnValue(method = "getSkin", at = @At("RETURN"))
    private PlayerSkin replaceSkinCapeIfNeeded(PlayerSkin skin) {
        var devCape = CapeUtils.INSTANCE.getDevCape(profile.getId());

        if (devCape != null && skin.capeTexture() == null) {
            var playerSkin = new PlayerSkin(skin.texture(), skin.textureUrl(), devCape.getLocation(), skin.elytraTexture(), skin.model(), skin.secure());

            this.skinLookup = () -> playerSkin;
            return playerSkin;
        }

        for (@NotNull CapeStyle value : CapeStyle.getEntries()) {
            if (Objects.equals(value.getLocation(), skin.capeTexture())) {
                if (devCape == null) {
                    var playerSkin = new PlayerSkin(skin.texture(), skin.textureUrl(), null, skin.elytraTexture(), skin.model(), skin.secure());

                    this.skinLookup = () -> playerSkin;
                    return playerSkin;
                } else if (!Objects.equals(skin.capeTexture(), devCape.getLocation())) {
                    var playerSkin = new PlayerSkin(skin.texture(), skin.textureUrl(), devCape.getLocation(), skin.elytraTexture(), skin.model(), skin.secure());

                    this.skinLookup = () -> playerSkin;
                    return playerSkin;
                }
            }
        }

        return skin;
    }
}
