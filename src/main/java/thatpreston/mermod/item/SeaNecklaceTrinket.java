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

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import thatpreston.mermod.utils.SeaNecklaceUtils;

import java.util.List;

public class SeaNecklaceTrinket extends TrinketItem implements DyeableItem, ISeaNecklace {
    public SeaNecklaceTrinket() {
        super(new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1));
    }
    @Override
    public int getColor(ItemStack stack) {
        NbtCompound compoundTag = stack.getSubNbt("display");
        return compoundTag != null && compoundTag.contains("color", 99) ? compoundTag.getInt("color") : 16777215;
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
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        SeaNecklaceUtils.giveNecklaceEffects(entity);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        SeaNecklaceUtils.appendTooltip(stack, tooltip);
    }
}