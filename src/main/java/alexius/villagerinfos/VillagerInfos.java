package alexius.villagerinfos;

import fi.dy.masa.malilib.MaLiLibInitHandler;
import fi.dy.masa.malilib.event.InitializationHandler;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import alexius.villagerinfos.ui.ModConfig;

public class VillagerInfos implements ModInitializer {
    public static final String MOD_ID = "villager-infos";
    public static ModConfig config;
    public static Logger logger = LogManager.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        InitializationHandler.getInstance().registerInitializationHandler(new InitHandler());
    }
}
