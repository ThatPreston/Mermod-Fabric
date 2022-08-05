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