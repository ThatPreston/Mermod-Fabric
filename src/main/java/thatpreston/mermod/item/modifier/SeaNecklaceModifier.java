package thatpreston.mermod.item.modifier;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SeaNecklaceModifier extends Item {
    private final NecklaceModifiers modifierType;
    public SeaNecklaceModifier(NecklaceModifiers type) {
        super(new FabricItemSettings().maxCount(1));
        this.modifierType = type;
    }
    public NecklaceModifiers getModifierType() {
        return modifierType;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        return modifierType.getHasGlint();
    }
}