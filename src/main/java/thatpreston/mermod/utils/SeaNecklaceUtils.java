package thatpreston.mermod.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import thatpreston.mermod.Mermod;
import thatpreston.mermod.RegistryHandler;
import thatpreston.mermod.item.modifier.NecklaceModifiers;
import thatpreston.mermod.item.modifier.SeaNecklaceModifier;

import java.util.ArrayList;
import java.util.List;

public class SeaNecklaceUtils {
    public static void addModifier(ItemStack necklace, ItemStack stack) {
        NbtCompound compound = necklace.getOrCreateSubNbt("necklace_modifiers");
        SeaNecklaceModifier modifier = (SeaNecklaceModifier)stack.getItem();
        String id = modifier.getModifierType().getId();
        String category = modifier.getModifierType().getCategory();
        compound.putString(category, id);
        if(modifier instanceof DyeableItem) {
            compound.putInt(category + "_color", ((DyeableItem)modifier).getColor(stack));
        }
    }
    public static ItemStack removeModifier(ItemStack necklace, SeaNecklaceModifier modifier) {
        NbtCompound compound = necklace.getOrCreateSubNbt("necklace_modifiers");
        NecklaceModifiers modifierType = modifier.getModifierType();
        String id = modifierType.getId();
        String category = modifierType.getCategory();
        if(compound.getString(category).equals(id)) {
            ItemStack stack = new ItemStack(modifier);
            compound.remove(category);
            if(modifierType.isColorable()) {
                stack.getOrCreateSubNbt("display").putInt("color", compound.getInt(category + "_color"));
                compound.remove(category + "_color");
            }
            return stack;
        }
        return null;
    }
    public static ItemStack removeModifier(ItemStack necklace) {
        for(int i = 0; i < RegistryHandler.NECKLACE_MODIFIERS.size(); i++) {
            SeaNecklaceModifier modifier = RegistryHandler.NECKLACE_MODIFIERS.get(i);
            ItemStack stack = removeModifier(necklace, modifier);
            if(stack != null) {
                return stack;
            }
        }
        return null;
    }
    public static boolean hasModifier(ItemStack necklace, NecklaceModifiers modifierType) {
        NbtCompound compound = necklace.getOrCreateSubNbt("necklace_modifiers");
        return compound.contains(modifierType.getCategory());
    }
    public static boolean canAddModifiers(ItemStack necklace, List<ItemStack> modifiers) {
        ArrayList<String> presentModifiers = new ArrayList<>();
        for(int i = 0; i < modifiers.size(); i++) {
            SeaNecklaceModifier modifier = (SeaNecklaceModifier)modifiers.get(i).getItem();
            String category = modifier.getModifierType().getCategory();
            if(hasModifier(necklace, modifier.getModifierType()) || presentModifiers.contains(category)) {
                return false;
            }
            presentModifiers.add(category);
        }
        return true;
    }
    public static void addModifiers(ItemStack necklace, List<ItemStack> modifiers) {
        for(int i = 0; i < modifiers.size(); i++) {
            addModifier(necklace, modifiers.get(i));
        }
    }
    public static void appendTooltip(ItemStack necklace, List<Text> tooltip) {
        NbtCompound compound = necklace.getOrCreateSubNbt("necklace_modifiers");
        int count = 0;
        for(NecklaceModifiers modifierType : NecklaceModifiers.values()) {
            String id = modifierType.getId();
            String category = modifierType.getCategory();
            if(compound.getString(category).equals(id)) {
                TranslatableText text = new TranslatableText("item.mermod." + id + "_modifier");
                if(modifierType.isColorable()) {
                    text.setStyle(Style.EMPTY.withColor(compound.getInt(category + "_color")));
                } else {
                    text.setStyle(Style.EMPTY.withColor(modifierType.getTooltipColor()));
                }
                tooltip.add(text);
                count++;
            }
        }
        if(count > 0) {
            tooltip.add(new TranslatableText("tooltip.mermod.modifierHint").formatted(Formatting.GRAY));
        }
    }
    public static void giveNecklaceEffects(LivingEntity entity) {
        if(Mermod.checkTailConditions(entity)) {
            if(Mermod.MERMOD_CONFIG.waterBreathing && !entity.hasStatusEffect(StatusEffects.WATER_BREATHING)) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 200, 0, false, false));
            }
            if(Mermod.MERMOD_CONFIG.nightVision && !entity.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 200, 0, false, false));
            }
        }
    }
}