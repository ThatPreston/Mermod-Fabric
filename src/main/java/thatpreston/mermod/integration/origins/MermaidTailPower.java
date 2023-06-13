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