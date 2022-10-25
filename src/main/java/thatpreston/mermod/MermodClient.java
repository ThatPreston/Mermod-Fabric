package thatpreston.mermod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
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