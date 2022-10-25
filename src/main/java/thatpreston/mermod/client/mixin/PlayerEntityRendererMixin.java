package thatpreston.mermod.client.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thatpreston.mermod.Mermod;
import thatpreston.mermod.client.render.MermaidTailStyle;
import thatpreston.mermod.client.render.TailFeatureRenderer;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> implements LivingEntityFeatureRendererRegistrationCallback {
    public PlayerEntityRendererMixin(Context context, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }
    @Inject(method = "<init>(Lnet/minecraft/client/render/entity/EntityRendererFactory$Context;Z)V", at = @At("TAIL"))
    public void onConstructor(Context context, boolean slim, CallbackInfo info) {
        this.addFeature(new TailFeatureRenderer(this, context.getModelLoader()));
    }
    @Inject(method = "setModelPose", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isInSneakingPose()Z", shift = At.Shift.AFTER))
    public void onSetModelPose(AbstractClientPlayerEntity player, CallbackInfo info) {
        if(Mermod.checkTailConditions(player)) {
            MermaidTailStyle style = Mermod.getTailStyle(player);
            if (style != null) {
                this.getModel().rightLeg.visible = false;
                this.getModel().leftLeg.visible = false;
                this.getModel().rightPants.visible = false;
                this.getModel().leftPants.visible = false;
            }
        }
    }
}