package thatpreston.mermod.client.mixin;

import org.moon.figura.avatar.Avatar;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thatpreston.mermod.integration.figura.MermodFiguraAPI;

import java.util.UUID;

@Mixin(Avatar.class)
public class AvatarMixin {
    @Shadow @Final public UUID owner;
    @Inject(method = "clean", at = @At("HEAD"), remap = false)
    public void onClean(CallbackInfo info) {
        MermodFiguraAPI.TAIL_VISIBLE.remove(this.owner);
    }
}