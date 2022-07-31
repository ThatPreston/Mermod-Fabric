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