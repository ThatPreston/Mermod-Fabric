package thatpreston.mermod.client.mixin;

import net.blancworks.figura.avatar.AvatarData;
import net.blancworks.figura.avatar.LocalAvatarData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thatpreston.mermod.integration.figura.MermodFiguraAPI;

import java.util.UUID;

@Mixin(LocalAvatarData.class)
public abstract class LocalAvatarDataMixin extends AvatarData {
    public LocalAvatarDataMixin(UUID id) {
        super(id);
    }
    @Inject(method = "loadModelFile", at = @At("HEAD"), remap = false)
    public void onLoadModelFile(CallbackInfo info) {
        if(this.entityId != null) {
            MermodFiguraAPI.DISABLED.remove(this.entityId);
        }
    }
}