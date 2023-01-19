package alexius.villagerinfos.ui;

import alexius.villagerinfos.VillagerInfos;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip;
import org.apache.logging.log4j.Level;

@Config(name = "villager-infos")
public class ModConfig implements ConfigData {

    @Tooltip()
    boolean modRenderingEnabled = true;
    @Tooltip()
    boolean renderVillagerNameTagInfos = true;
    @Tooltip()
    boolean renderVillagerTask = true;
    @Tooltip()
    boolean renderVillagerReputation = false;
    @Tooltip()
    boolean renderVillagerLevel = true;
    @Tooltip()
    boolean renderVillagerJob = true;
    @Tooltip()
    boolean renderVillagerType = false;
    @Tooltip()
    boolean renderVillagerCanGolem = false;
    @Tooltip()
    boolean renderVillagerCanBreed = false;
    @Tooltip()
    boolean renderVillagerOnFire = false;
    @Tooltip()
    boolean renderVillagerCanRestock = true;
    @Tooltip()
    boolean renderVillagerUUID = false;
    @Tooltip()
    boolean renderVillagerActivity = true;
    @Tooltip()
    float displaySize = .03f;

    public boolean isModRenderingEnabled() {
        VillagerInfos.logger.log(Level.INFO, "rendering enabled" + modRenderingEnabled);
        return modRenderingEnabled;
    }
    public boolean isRenderVillagerTask() {
        return renderVillagerTask;
    }

    public boolean isRenderVillagerNameTagInfos() {
        return renderVillagerNameTagInfos;
    }

    public boolean isRenderVillagerReputation() {
        return renderVillagerReputation;
    }
    public float getDisplaySize() {
        return displaySize;
    }

    public boolean isRenderVillagerLevel() {
        return renderVillagerLevel;
    }

    public boolean isRenderVillagerJob() {
        return renderVillagerJob;
    }

    public boolean isRenderVillagerType() {
        return renderVillagerType;
    }


    public boolean isRenderVillagerCanGolem() {
        return renderVillagerCanGolem;
    }

    public boolean isRenderVillagerCanBreed() {
        return renderVillagerCanBreed;
    }


    public boolean isRenderVillagerOnFire() {
        return renderVillagerOnFire;
    }

    public boolean isRenderVillagerCanRestock() {
        return renderVillagerCanRestock;
    }


    public boolean isRenderVillagerUUID() {
        return renderVillagerUUID;
    }

    public boolean isRenderVillagerActivity() {
        return renderVillagerActivity;
    }
}
