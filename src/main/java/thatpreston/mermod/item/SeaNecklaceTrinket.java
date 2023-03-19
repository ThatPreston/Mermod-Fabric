package thatpreston.mermod.item;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
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
        super(new FabricItemSettings().maxCount(1));
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