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