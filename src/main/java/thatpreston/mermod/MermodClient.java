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

package thatpreston.mermod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.item.DyeableItem;
import net.minecraft.util.Identifier;
import thatpreston.mermod.client.render.TailModel;

public class MermodClient implements ClientModInitializer {
    public static final EntityModelLayer TAIL = new EntityModelLayer(new Identifier("mermod", "tail"), "tail");
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(TAIL, () -> TailModel.getTexturedModelData());
        ColorProviderRegistry.ITEM.register((stack, index) -> index > 0 ? ((DyeableItem)stack.getItem()).getColor(stack) : -1, RegistryHandler.SEA_NECKLACE);
        ColorProviderRegistry.ITEM.register((stack, index) -> ((DyeableItem)stack.getItem()).getColor(stack), RegistryHandler.MERMAID_BRA_MODIFIER, RegistryHandler.TAIL_GRADIENT_MODIFIER);
    }
}