package thatpreston.mermod.integration.figura;

import org.moon.figura.avatar.Avatar;
import org.moon.figura.lua.FiguraAPI;
import org.moon.figura.lua.LuaWhitelist;

import java.util.*;

@LuaWhitelist
public class MermodFiguraAPI implements FiguraAPI {
    public static final Map<UUID, Boolean> TAIL_VISIBLE = new HashMap<>();
    private UUID owner;
    public MermodFiguraAPI() {}
    public MermodFiguraAPI(UUID owner) {
        this.owner = owner;
    }
    @LuaWhitelist
    public void setVisible(boolean value) {
        TAIL_VISIBLE.put(owner, value);
    }
    @Override
    public FiguraAPI build(Avatar avatar) {
        return new MermodFiguraAPI(avatar.owner);
    }
    @Override
    public String getName() {
        return "mermod_tail";
    }
    @Override
    public Collection<Class<?>> getWhitelistedClasses() {
        return Collections.singleton(MermodFiguraAPI.class);
    }
    public static boolean isTailVisible(UUID owner) {
        return TAIL_VISIBLE.getOrDefault(owner, true);
    }
}