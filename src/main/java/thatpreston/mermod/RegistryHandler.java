package thatpreston.mermod;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
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
        if(Mermod.trinketsInstalled) {
            SEA_NECKLACE = TrinketsIntegration.getNecklaceItem();
        } else {
            SEA_NECKLACE = new SeaNecklace();
        }
        SEA_CRYSTAL = new Item(new FabricItemSettings());
        MERMAID_BRA_MODIFIER = new DyeableSeaNecklaceModifier(NecklaceModifiers.MERMAID_BRA);
        TAIL_GRADIENT_MODIFIER = new DyeableSeaNecklaceModifier(NecklaceModifiers.TAIL_GRADIENT);
        GLOWING_PEARL_MODIFIER = new SeaNecklaceModifier(NecklaceModifiers.GLOWING_PEARL);
        MOON_ROCK_MODIFIER = new SeaNecklaceModifier(NecklaceModifiers.MOON_ROCK);
        URSULA_SHELL_MODIFIER = new SeaNecklaceModifier(NecklaceModifiers.URSULA_SHELL);
        NECKLACE_MODIFIER_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier("mermod", "crafting_special_necklacemodifier"), new SpecialRecipeSerializer<>(NecklaceModifierRecipe::new));
        Registry.register(Registries.RECIPE_TYPE, new Identifier("mermod", "necklace_modifier"), new RecipeType<NecklaceModifierRecipe>(){});
        Registry.register(Registries.ITEM, new Identifier("mermod", "sea_necklace"), SEA_NECKLACE);
        Registry.register(Registries.ITEM, new Identifier("mermod", "sea_crystal"), SEA_CRYSTAL);
        registerNecklaceModifier(MERMAID_BRA_MODIFIER, "mermaid_bra");
        registerNecklaceModifier(TAIL_GRADIENT_MODIFIER, "tail_gradient");
        registerNecklaceModifier(GLOWING_PEARL_MODIFIER, "glowing_pearl");
        registerNecklaceModifier(MOON_ROCK_MODIFIER, "moon_rock");
        registerNecklaceModifier(URSULA_SHELL_MODIFIER, "ursula_shell");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(SEA_NECKLACE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(SEA_CRYSTAL);
            for(SeaNecklaceModifier modifier : NECKLACE_MODIFIERS) {
                entries.add(modifier);
            }
        });
    }
    public static void registerNecklaceModifier(SeaNecklaceModifier modifier, String id) {
        NECKLACE_MODIFIERS.add(modifier);
        Registry.register(Registries.ITEM, new Identifier("mermod", id + "_modifier"), modifier);
    }
}