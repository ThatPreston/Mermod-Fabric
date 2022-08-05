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

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import thatpreston.mermod.client.render.MermaidTailStyle;

import java.util.List;

public class OriginsIntegration {
    private static final Identifier FACTORY_IDENTIFIER = new Identifier("mermod", "tail_style");
    public static boolean hasTailPower(PlayerEntity player) {
        return PowerHolderComponent.hasPower(player, MermaidTailPower.class);
    }
    public static MermaidTailStyle getTailStyle(PlayerEntity player) {
        List<MermaidTailPower> powers = PowerHolderComponent.getPowers(player, MermaidTailPower.class);
        if(powers != null && powers.size() > 0) {
            MermaidTailPower power = powers.get(0);
            return new MermaidTailStyle(power.getTailColor(), power.getHasBra(), power.getBraColor(), power.getHasGradient(), power.getGradientColor(), power.getHasGlint(), power.getTexture());
        }
        return null;
    }
    public static void registerPowerFactory() {
        SerializableData serializableData = new SerializableData();
        serializableData.add("tailColor", SerializableDataTypes.INT, 16777215);
        serializableData.add("hasBra", SerializableDataTypes.BOOLEAN, false);
        serializableData.add("braColor", SerializableDataTypes.INT, 16777215);
        serializableData.add("hasGradient", SerializableDataTypes.BOOLEAN, false);
        serializableData.add("gradientColor", SerializableDataTypes.INT, 16777215);
        serializableData.add("hasGlint", SerializableDataTypes.BOOLEAN, false);
        serializableData.add("texture", SerializableDataTypes.IDENTIFIER, MermaidTailStyle.DEFAULT_TEXTURE);
        PowerFactory serializer = new PowerFactory<>(FACTORY_IDENTIFIER, serializableData, data -> (type, entity) -> {
            return new MermaidTailPower(type, entity, data.getInt("tailColor"), data.getBoolean("hasBra"), data.getInt("braColor"), data.getBoolean("hasGradient"), data.getInt("gradientColor"), data.getBoolean("hasGlint"), data.getId("texture"));
        }).allowCondition();
        Registry.register(ApoliRegistries.POWER_FACTORY, serializer.getSerializerId(), serializer);
    }
}