package thatpreston.mermod.item.modifier;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DyeableSeaNecklaceModifier extends SeaNecklaceModifier implements DyeableItem {
    public DyeableSeaNecklaceModifier(NecklaceModifiers type) {
        super(type);
    }
    @Override
    public int getColor(ItemStack stack) {
        NbtCompound compound = stack.getSubNbt("display");
        return compound != null && compound.contains("color", 99) ? compound.getInt("color") : 16777215;
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.mermod.canBeDyed").formatted(Formatting.GRAY));
    }
}