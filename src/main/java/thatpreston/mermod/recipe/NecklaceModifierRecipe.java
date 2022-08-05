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

package thatpreston.mermod.recipe;

import com.google.common.collect.Lists;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import thatpreston.mermod.utils.SeaNecklaceUtils;
import thatpreston.mermod.RegistryHandler;
import thatpreston.mermod.item.ISeaNecklace;
import thatpreston.mermod.item.modifier.SeaNecklaceModifier;

import java.util.List;

public class NecklaceModifierRecipe extends SpecialCraftingRecipe {
    public NecklaceModifierRecipe(Identifier identifier) {
        super(identifier);
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
    public ItemStack craft(CraftingInventory inventory) {
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