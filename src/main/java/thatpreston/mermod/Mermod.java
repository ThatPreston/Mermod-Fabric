package thatpreston.mermod;

import draylar.omegaconfig.OmegaConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import thatpreston.mermod.integration.figura.MermodFiguraAPI;
import thatpreston.mermod.integration.omegaconfig.MermodConfig;
import thatpreston.mermod.integration.origins.OriginsIntegration;
import thatpreston.mermod.integration.trinkets.TrinketsIntegration;
import thatpreston.mermod.item.SeaNecklace;
import thatpreston.mermod.client.render.MermaidTailStyle;

public class Mermod implements ModInitializer {
    public static final MermodConfig MERMOD_CONFIG = OmegaConfig.register(MermodConfig.class);
    public static boolean originsInstalled;
    public static boolean trinketsInstalled;
    public static boolean modMenuInstalled;
    public static boolean figuraInstalled;
    @Override
    public void onInitialize() {
        FabricLoader loader = FabricLoader.getInstance();
        originsInstalled = loader.isModLoaded("origins");
        trinketsInstalled = loader.isModLoaded("trinkets");
        modMenuInstalled = loader.isModLoaded("modmenu");
        figuraInstalled = loader.isModLoaded("figura");
        if(originsInstalled) {
            OriginsIntegration.registerPowerFactory();
        }
        RegistryHandler.init();
        registerCauldronBehavior();
    }
    private void registerCauldronBehavior() {
        CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(RegistryHandler.SEA_NECKLACE, CauldronBehavior.CLEAN_DYEABLE_ITEM);
        CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(RegistryHandler.MERMAID_BRA_MODIFIER, CauldronBehavior.CLEAN_DYEABLE_ITEM);
        CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(RegistryHandler.TAIL_GRADIENT_MODIFIER, CauldronBehavior.CLEAN_DYEABLE_ITEM);
    }
    public static ItemStack getNecklace(PlayerEntity player) {
        ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
        if(!chest.isEmpty() && chest.getItem() instanceof SeaNecklace) {
            return chest;
        }
        if(trinketsInstalled) {
            return TrinketsIntegration.getNecklaceStack(player);
        }
        return ItemStack.EMPTY;
    }
    public static boolean checkTailConditions(Entity entity) {
        if(!entity.isInvisible() && entity.isTouchingWater()) {
            if(figuraInstalled) {
                return !MermodFiguraAPI.checkDisabled(entity);
            }
            return true;
        }
        return false;
    }
    public static boolean getPlayerHasTail(PlayerEntity player) {
        ItemStack necklace = getNecklace(player);
        if(!necklace.isEmpty()) {
            return true;
        } else if(originsInstalled) {
            return OriginsIntegration.hasTailPower(player);
        }
        return false;
    }
    public static MermaidTailStyle getTailStyle(PlayerEntity player) {
        ItemStack necklace = getNecklace(player);
        if(!necklace.isEmpty()) {
            return new MermaidTailStyle(necklace);
        } else if(Mermod.originsInstalled && OriginsIntegration.hasTailPower(player)) {
            return OriginsIntegration.getTailStyle(player);
        }
        return null;
    }
}