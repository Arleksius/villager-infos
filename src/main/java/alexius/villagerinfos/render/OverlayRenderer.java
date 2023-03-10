package alexius.villagerinfos.render;

import alexius.villagerinfos.VillagerInfos;
import alexius.villagerinfos.ui.ModConfig;
import com.mojang.blaze3d.platform.TextureUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import fi.dy.masa.malilib.render.RenderUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.render.*;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.data.client.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.TradeOffer;
import net.minecraft.world.World;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.apache.logging.log4j.Level;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class OverlayRenderer {

    static List<String> informations;

    public static void renderOverlays(MatrixStack matrixStack, Matrix4f projMatrix, MinecraftClient mc){
        ModConfig config = VillagerInfos.config;
        if (config.isModRenderingEnabled()){
            if (config.isRenderVillagerNameTagInfos()){
                renderVillagerTasks(mc, config);
            }
            if (config.isRenderVillagerOther()){
                renderVillagerOther(mc, config, projMatrix);
            }
        }
    }

    public static void renderVillagerOther(MinecraftClient mc, ModConfig config, Matrix4f projMatrix){
        GetVillagers().forEach(villager -> {
            if (villager.getUuid().toString().equals(config.getSelectedVillager())){
                Item item = villager.getOffers().get(0).getSellItem().getItem();
                RenderUtils.bindTexture(TextureMap.getId(item));
                //RenderUtils.drawTexturedRect();
            }
            RenderSystem.setShader(GameRenderer::getPositionColorProgram);
            RenderSystem.applyModelViewMatrix();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();

            RenderUtils.setupBlend();

            buffer.begin(VertexFormat.DrawMode.LINE_STRIP, VertexFormats.POSITION_COLOR);

            /*buffer.vertex(
                    projMatrix,
                    (float) villager.getX(),
                    (float) villager.getY(),
                    (float) villager.getZ()
            ).color(0,);
            */
            tessellator.draw();

            RenderSystem.disableBlend();
        });
    }

    public static void renderVillagerTasks(MinecraftClient mc, ModConfig config){
        World world = mc.world;
        if (world == null) return;
        List<VillagerEntity> villagers = GetVillagers();

        if (villagers == null || villagers.size() == 0) return;
        if (mc.player == null) return;
        for (VillagerEntity v:villagers) {
            informations = new ArrayList<>();
            if (config.isRenderVillagerTask())
                informations.add("Task: " + v.getBrain().getSchedule().getActivityForTime((int)world.getTimeOfDay()).toString());
            if (config.isRenderVillagerReputation())
                informations.add("Rep: " + v.getGossip().getReputationFor(mc.player.getUuid(), villageGossipType -> true));
            if (config.isRenderVillagerLevel())
                informations.add("Lvl: " + v.getVillagerData().getLevel());
            if (config.isRenderVillagerJob())
                informations.add("Job: " + v.getVillagerData().getProfession().toString());
            if (config.isRenderVillagerType())
                informations.add("Type: " + v.getVillagerData().getType().toString());
            if (config.isRenderVillagerCanGolem())
                informations.add("CanGolem: " + v.canSummonGolem(0));
            if (config.isRenderVillagerCanBreed())
                informations.add("CanBreed:" + v.canBreed());
            if (config.isRenderVillagerOnFire())
                informations.add("OnFire: " + v.getFireTicks());
            if (config.isRenderVillagerCanRestock())
                informations.add("CanRestock: " + v.canRefreshTrades());
            if (config.isRenderVillagerUUID())
                informations.add("UUID: " + v.getUuid());
            if (false)
                informations.add("noClip: " + v.noClip);
            if (config.isRenderVillagerActivity())
                informations.add("Activity: " + v.getBrain().getFirstPossibleNonCoreActivity().get());
            Vec3d eyePos = v.getEyePos();
            Vec3d pos = new Vec3d(eyePos.x, eyePos.y + 1.5, eyePos.z);
            VillagerInfos.logger.log(Level.INFO, v.getEntityName());
            RenderUtils.drawTextPlate(informations, pos.x, pos.y, pos.z, config.getDisplaySize());

        }

    }

    public static List<VillagerEntity> GetVillagers(){
        List<VillagerEntity> villagers = new ArrayList<>();

        ClientWorld world = MinecraftClient.getInstance().world;
        if (world == null) return null;

        for (Entity e:world.getEntities()) {
            if (e instanceof VillagerEntity){
                villagers.add((VillagerEntity) e);
            }
        }
        return villagers;
    }
}
