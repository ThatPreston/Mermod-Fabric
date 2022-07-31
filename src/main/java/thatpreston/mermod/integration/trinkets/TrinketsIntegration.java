package thatpreston.mermod.integration.trinkets;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import thatpreston.mermod.RegistryHandler;
import thatpreston.mermod.item.SeaNecklaceTrinket;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TrinketsIntegration {
    public static Item getNecklaceItem() {
        return new SeaNecklaceTrinket();
    }
    public static ItemStack getNecklaceStack(PlayerEntity player) {
        AtomicReference<ItemStack> stack = new AtomicReference<>(ItemStack.EMPTY);
        TrinketsApi.getTrinketComponent(player).ifPresent(component -> {
            List<Pair<SlotReference, ItemStack>> list = component.getEquipped(RegistryHandler.SEA_NECKLACE);
            if(list.size() > 0) {
                stack.set(list.get(0).getRight());
            }
        });
        return stack.get();
    }
}