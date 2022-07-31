package thatpreston.mermod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import thatpreston.mermod.utils.SeaNecklaceUtils;

import java.util.List;

public class SeaNecklace extends Item implements DyeableItem, ISeaNecklace {
    public SeaNecklace() {
        super(new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1).equipmentSlot(stack -> EquipmentSlot.CHEST));
    }
    @Override
    public int getColor(ItemStack stack) {
        NbtCompound compound = stack.getSubNbt("display");
        return compound != null && compound.contains("color", 99) ? compound.getInt("color") : 16777215;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack necklace = player.getStackInHand(hand);
        if(player.isSneaking()) {
            ItemStack stack = SeaNecklaceUtils.removeModifier(necklace);
            if(stack != null) {
                player.giveItemStack(stack);
                return TypedActionResult.success(necklace, world.isClient());
            }
        }
        return super.use(world, player, hand);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(entity instanceof LivingEntity && slot == 2) {
            LivingEntity livingEntity = (LivingEntity)entity;
            if(livingEntity.getEquippedStack(EquipmentSlot.CHEST).equals(stack)) {
                SeaNecklaceUtils.giveNecklaceEffects(livingEntity);
            }
        }
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        SeaNecklaceUtils.appendTooltip(stack, tooltip);
    }
}