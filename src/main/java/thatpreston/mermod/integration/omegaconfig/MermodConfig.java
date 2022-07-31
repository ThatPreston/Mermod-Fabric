package thatpreston.mermod.integration.omegaconfig;

import draylar.omegaconfig.api.Config;
import draylar.omegaconfig.api.Syncing;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import org.jetbrains.annotations.Nullable;

public class MermodConfig implements Config {
    @Syncing
    @Comment(value = "Increased swimming speed as a mermaid")
    public boolean swimSpeed = true;
    @Syncing
    @Comment(value = "Speed multiplier applied while swimming if the swim speed effect is enabled")
    public float swimSpeedMultiplier = 2;
    @Syncing
    @Comment(value = "Unlimited water breathing as a mermaid")
    public boolean waterBreathing = true;
    @Syncing
    @Comment(value = "Underwater night vision as a mermaid")
    public boolean nightVision = true;
    @Comment(value = "Removes the flashing effect when night vision is almost expired")
    public boolean nightVisionFlashingFix = true;
    @Override
    public String getName() {
        return "mermod";
    }
    @Override
    public @Nullable String getModid() {
        return "mermod";
    }
}