/*
Mermod adds a magical necklace that transforms you into a merperson in wate
Copyright (C) 2022 ThatPreston
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

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