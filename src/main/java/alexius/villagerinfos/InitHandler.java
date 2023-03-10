package alexius.villagerinfos;

import alexius.villagerinfos.event.RenderHandler;
import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.RenderEventHandler;
import fi.dy.masa.malilib.interfaces.IInitializationHandler;

public class InitHandler implements IInitializationHandler {
    @Override
    public void registerModHandlers() {
        //ConfigManager.getInstance().registerConfigHandler(Reference.MOD_ID, new Configs());
        //InputEventHandler.getKeybindManager().registerKeybindProvider(InputHandler.getInstance());
        //InputEventHandler.getInputManager().registerMouseInputHandler(InputHandler.getInstance());

        RenderHandler renderer = RenderHandler.getInstance();
        //RenderEventHandler.getInstance().registerGameOverlayRenderer(renderer);
        //RenderEventHandler.getInstance().registerTooltipLastRenderer(renderer);
        RenderEventHandler.getInstance().registerWorldLastRenderer(renderer);

        //WorldLoadListener listener = new WorldLoadListener();
        //WorldLoadHandler.getInstance().registerWorldLoadPreHandler(listener);
        //WorldLoadHandler.getInstance().registerWorldLoadPostHandler(listener);

        //TickHandler.getInstance().registerClientTickHandler(new ClientTickHandler());

        //KeyCallbacks.init();
    }
}
