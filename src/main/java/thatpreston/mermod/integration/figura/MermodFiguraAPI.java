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

package thatpreston.mermod.integration.figura;

import net.blancworks.figura.lua.CustomScript;
import net.blancworks.figura.lua.api.FiguraAPI;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MermodFiguraAPI implements FiguraAPI {
    public static Map<UUID, Boolean> DISABLED = new HashMap<>();
    @Override
    public Identifier getID() {
        return new Identifier("mermod", "mermod_tail");
    }
    @Override
    public LuaTable getForScript(CustomScript script) {
        return new LuaTable() {{
            set("setDisabled", new OneArgFunction() {
                @Override
                public LuaValue call(LuaValue value) {
                    boolean disabled = value.checkboolean();
                    DISABLED.put(script.avatarData.entityId, disabled);
                    return NIL;
                }
            });
        }};
    }
    public static boolean checkDisabled(Entity entity) {
        return DISABLED.getOrDefault(entity.getUuid(), false);
    }
}