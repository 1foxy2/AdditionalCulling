package ca.fxco.moreculling;

import ca.fxco.moreculling.config.ConfigUpdater;
import ca.fxco.moreculling.config.ModMenuConfig;
import ca.fxco.moreculling.config.MoreCullingConfig;
import ca.fxco.moreculling.utils.CompatUtils;
import com.terraformersmc.mod_menu.ModMenu;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(value = MoreCulling.MOD_ID, dist = Dist.CLIENT)
public class MoreCulling {

    public static int CURRENT_VERSION = 1;

    public static ModelManager bakedModelManager = null;
    public static BlockRenderDispatcher blockRenderManager = null;

    public static final String MOD_ID = "additionalculling";
    public static final TagKey<Block> DONT_CULL = TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath(MOD_ID, "dont_cull"));

    public static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static MoreCullingConfig CONFIG;

    public MoreCulling() {
        AutoConfig.register(MoreCullingConfig.class, (conf, clazz) -> new Toml4jConfigSerializer<>(conf, clazz) {
            public MoreCullingConfig deserialize() {
                try {
                    return super.deserialize();
                } catch (Exception e) {
                    return this.createDefault();
                }
            }
        });
        CONFIG = AutoConfig.getConfigHolder(MoreCullingConfig.class).getConfig();
        ConfigUpdater.updateConfig(CONFIG);
        CONFIG.modCompatibility.defaultReturnValue(CONFIG.useOnModdedBlocksByDefault);
        if (CompatUtils.IS_BETTER_MODLIST_LOADED)
            ModMenu.configScreenFactories.put(MOD_ID, ModMenuConfig::createConfigScreen);
    }

    public void saveConfig() {
        AutoConfig.getConfigHolder(MoreCullingConfig.class).save();
    }
}
