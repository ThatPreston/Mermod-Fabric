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

import draylar.omegaconfig.OmegaConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
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