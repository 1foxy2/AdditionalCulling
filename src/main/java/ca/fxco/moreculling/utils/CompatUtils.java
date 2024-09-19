package ca.fxco.moreculling.utils;

import net.neoforged.fml.ModList;

public class CompatUtils {
    public static final boolean IS_SODIUM_LOADED = ModList.get().isLoaded("sodium");
    public static final boolean IS_MODERNFIX_LOADED = ModList.get().isLoaded("modernfix");
    public static final boolean IS_BETTER_MODLIST_LOADED = ModList.get().isLoaded("mod_menu");
}
