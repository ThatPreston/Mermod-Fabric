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