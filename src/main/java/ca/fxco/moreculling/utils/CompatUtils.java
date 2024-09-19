package ca.fxco.moreculling.utils;

import net.neoforged.fml.loading.LoadingModList;

public class CompatUtils {
    public static final boolean IS_SODIUM_LOADED = LoadingModList.get().getModFileById("sodium") != null;
    public static final boolean IS_MODERNFIX_LOADED = LoadingModList.get().getModFileById("modernfix") != null;
}
