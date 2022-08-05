/*
Mermod adds a magical necklace that transforms you into a merperson in water!
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

package thatpreston.mermod.integration.origins;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class MermaidTailPower extends Power {
    private final int tailColor;
    private final boolean hasBra;
    private final int braColor;
    private final boolean hasGradient;
    private final int gradientColor;
    private final boolean hasGlint;
    private final Identifier texture;
    public MermaidTailPower(PowerType<?> type, LivingEntity player, int tailColor, boolean hasBra, int braColor, boolean hasGradient, int gradientColor, boolean hasGlint, Identifier texture) {
        super(type, player);
        this.tailColor = tailColor;
        this.hasBra = hasBra;
        this.braColor = braColor;
        this.hasGradient = hasGradient;
        this.gradientColor = gradientColor;
        this.hasGlint = hasGlint;
        this.texture = texture;
    }
    public int getTailColor() {
        return tailColor;
    }
    public boolean getHasBra() {
        return hasBra;
    }
    public int getBraColor() {
        return braColor;
    }
    public boolean getHasGradient() {
        return hasGradient;
    }
    public int getGradientColor() {
        return gradientColor;
    }
    public boolean getHasGlint() {
        return hasGlint;
    }
    public Identifier getTexture() {
        return texture;
    }
}