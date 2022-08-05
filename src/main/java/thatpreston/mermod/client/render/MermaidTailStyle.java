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

package thatpreston.mermod.client.render;

import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class MermaidTailStyle {
    public static final Identifier DEFAULT_TEXTURE = new Identifier("mermod", "textures/tail/tail.png");
    public static final Identifier ARIEL_TEXTURE = new Identifier("mermod", "textures/tail/tail_ariel.png");
    public static final Identifier ARIEL_TEXTURE_COLORABLE = new Identifier("mermod", "textures/tail/tail_ariel_colorable.png");
    public static final Identifier H2O_TEXTURE = new Identifier("mermod", "textures/tail/tail_h2o.png");
    public static final Identifier H2O_TEXTURE_COLORABLE = new Identifier("mermod", "textures/tail/tail_h2o_colorable.png");
    private final int tailColor;
    private final boolean hasBra;
    private final int braColor;
    private final boolean hasGradient;
    private final int gradientColor;
    private final boolean hasGlint;
    private final Identifier texture;
    public MermaidTailStyle(int tailColor, boolean hasBra, int braColor, boolean hasGradient, int gradientColor, boolean hasGlint, Identifier texture) {
        this.tailColor = tailColor;
        this.hasBra = hasBra;
        this.braColor = braColor;
        this.hasGradient = hasGradient;
        this.gradientColor = gradientColor;
        this.hasGlint = hasGlint;
        this.texture = texture;
    }
    public MermaidTailStyle(ItemStack necklace) {
        NbtCompound compound = necklace.getOrCreateSubNbt("necklace_modifiers");
        this.tailColor = ((DyeableItem)necklace.getItem()).getColor(necklace);
        this.hasBra = compound.contains("bra");
        this.braColor = compound.getInt("bra_color");
        this.hasGradient = compound.contains("gradient");
        this.gradientColor = compound.getInt("gradient_color");
        this.hasGlint = compound.contains("glint");
        String texture = compound.getString("texture");
        boolean defaultColor = this.tailColor == 16777215 && !this.hasGradient;
        if(texture.equals("moon_rock")) {
            this.texture = defaultColor ? H2O_TEXTURE : H2O_TEXTURE_COLORABLE;
        } else if(texture.equals("ursula_shell")) {
            this.texture = defaultColor ? ARIEL_TEXTURE : ARIEL_TEXTURE_COLORABLE;
        } else {
            this.texture = DEFAULT_TEXTURE;
        }
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