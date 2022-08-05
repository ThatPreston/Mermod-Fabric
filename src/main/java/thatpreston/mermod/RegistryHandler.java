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

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import thatpreston.mermod.integration.trinkets.TrinketsIntegration;
import thatpreston.mermod.item.modifier.DyeableSeaNecklaceModifier;
import thatpreston.mermod.item.SeaNecklace;
import thatpreston.mermod.item.modifier.NecklaceModifiers;
import thatpreston.mermod.item.modifier.SeaNecklaceModifier;
import thatpreston.mermod.recipe.NecklaceModifierRecipe;

import java.util.ArrayList;

public class RegistryHandler {
    public static Item SEA_NECKLACE;
    public static Item SEA_CRYSTAL;
    public static SeaNecklaceModifier MERMAID_BRA_MODIFIER;
    public static SeaNecklaceModifier TAIL_GRADIENT_MODIFIER;
    public static SeaNecklaceModifier GLOWING_PEARL_MODIFIER;
    public static SeaNecklaceModifier MOON_ROCK_MODIFIER;
    public static SeaNecklaceModifier URSULA_SHELL_MODIFIER;
    public static SpecialRecipeSerializer<NecklaceModifierRecipe> NECKLACE_MODIFIER_SERIALIZER;
    public static ArrayList<SeaNecklaceModifier> NECKLACE_MODIFIERS = new ArrayList<>();
    public static void init() {
        if(Mermod.trinketsInstalled && false) {
            SEA_NECKLACE = TrinketsIntegration.getNecklaceItem();
        } else {
            SEA_NECKLACE = new SeaNecklace();
        }
        SEA_CRYSTAL = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
        MERMAID_BRA_MODIFIER = new DyeableSeaNecklaceModifier(NecklaceModifiers.MERMAID_BRA);
        TAIL_GRADIENT_MODIFIER = new DyeableSeaNecklaceModifier(NecklaceModifiers.TAIL_GRADIENT);
        GLOWING_PEARL_MODIFIER = new SeaNecklaceModifier(NecklaceModifiers.GLOWING_PEARL);
        MOON_ROCK_MODIFIER = new SeaNecklaceModifier(NecklaceModifiers.MOON_ROCK);
        URSULA_SHELL_MODIFIER = new SeaNecklaceModifier(NecklaceModifiers.URSULA_SHELL);
        NECKLACE_MODIFIER_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier("mermod", "crafting_special_necklacemodifier"), new SpecialRecipeSerializer<>(NecklaceModifierRecipe::new));
        Registry.register(Registry.RECIPE_TYPE, new Identifier("mermod", "necklace_modifier"), new RecipeType<NecklaceModifierRecipe>(){});
        Registry.register(Registry.ITEM, new Identifier("mermod", "sea_necklace"), SEA_NECKLACE);
        Registry.register(Registry.ITEM, new Identifier("mermod", "sea_crystal"), SEA_CRYSTAL);
        registerNecklaceModifier(MERMAID_BRA_MODIFIER, "mermaid_bra");
        registerNecklaceModifier(TAIL_GRADIENT_MODIFIER, "tail_gradient");
        registerNecklaceModifier(GLOWING_PEARL_MODIFIER, "glowing_pearl");
        registerNecklaceModifier(MOON_ROCK_MODIFIER, "moon_rock");
        registerNecklaceModifier(URSULA_SHELL_MODIFIER, "ursula_shell");
    }
    public static void registerNecklaceModifier(SeaNecklaceModifier modifier, String id) {
        NECKLACE_MODIFIERS.add(modifier);
        Registry.register(Registry.ITEM, new Identifier("mermod", id + "_modifier"), modifier);
    }
}