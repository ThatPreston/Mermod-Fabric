package thatpreston.mermod.recipe;

import com.google.common.collect.Lists;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import thatpreston.mermod.utils.SeaNecklaceUtils;
import thatpreston.mermod.RegistryHandler;
import thatpreston.mermod.item.ISeaNecklace;
import thatpreston.mermod.item.modifier.SeaNecklaceModifier;

import java.util.List;

public class NecklaceModifierRecipe extends SpecialCraftingRecipe {
    public NecklaceModifierRecipe(Identifier identifier, CraftingRecipeCategory category) {
        super(identifier, category);
    }
    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        ItemStack necklace = ItemStack.EMPTY;
        List<ItemStack> modifiers = Lists.newArrayList();
        for(int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if(!stack.isEmpty()) {
                if(stack.getItem() instanceof ISeaNecklace) {
                    if(!necklace.isEmpty()) {
                        return false;
                    }
                    necklace = stack;
                } else {
                    if(!(stack.getItem() instanceof SeaNecklaceModifier)) {
                        return false;
                    }
                    modifiers.add(stack);
                }
            }
        }
        return !necklace.isEmpty() && !modifiers.isEmpty() && SeaNecklaceUtils.canAddModifiers(necklace, modifiers);
    }
    @Override
    public ItemStack craft(CraftingInventory inventory, DynamicRegistryManager registryManager) {
        List<ItemStack> modifiers = Lists.newArrayList();
        ItemStack necklace = ItemStack.EMPTY;
        for(int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if(!stack.isEmpty()) {
                if(stack.getItem() instanceof ISeaNecklace) {
                    if(!necklace.isEmpty()) {
                        return ItemStack.EMPTY;
                    }
                    necklace = stack.copy();
                } else {
                    if(!(stack.getItem() instanceof SeaNecklaceModifier)) {
                        return ItemStack.EMPTY;
                    }
                    modifiers.add(stack);
                }
            }
        }
        if(!necklace.isEmpty() && !modifiers.isEmpty()) {
            SeaNecklaceUtils.addModifiers(necklace, modifiers);
            return necklace;
        }
        return ItemStack.EMPTY;
    }
    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return RegistryHandler.NECKLACE_MODIFIER_SERIALIZER;
    }
}