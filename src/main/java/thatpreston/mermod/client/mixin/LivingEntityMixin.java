package thatpreston.mermod.client.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import thatpreston.mermod.Mermod;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @ModifyVariable(method = "travel", at = @At(value = "STORE", ordinal = 0), ordinal = 1)
    public float modifyFloat(float original) {
        LivingEntity entity = (LivingEntity)(Object)this;
        if(Mermod.MERMOD_CONFIG.swimSpeed && Mermod.checkTailConditions(entity) && entity instanceof PlayerEntity && Mermod.getPlayerHasTail((PlayerEntity)entity)) {
            return original * Mermod.MERMOD_CONFIG.swimSpeedMultiplier;
        }
        return original;
    }
}