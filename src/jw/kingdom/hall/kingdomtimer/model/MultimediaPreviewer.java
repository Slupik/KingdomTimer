package jw.kingdom.hall.kingdomtimer.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import jw.kingdom.hall.kingdomtimer.device.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.view.common.controller.MultimediaPreviewController;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class MultimediaPreviewer {
    private static int REFRESH_INTERVAL = 500;

    private List<MultimediaPreviewController> controllers = new ArrayList<>();
    private Thread countdown;
    private boolean pause = true;
    private Monitor monitor;

    private void countDown() {
        if(!pause) {
            Image image = getMonitorScreen();
            notifyControllers(image);
        }
    }

    @Nullable
    private Image getMonitorScreen() {
        if(monitor!=null) {
            try {
                Rectangle areaToMakeSS = monitor.getDefaultConfiguration().getBounds();
                return SwingFXUtils.toFXImage(ScreenShotMaker.getSS(areaToMakeSS), null);
            } catch (AWTException | IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void notifyControllers(Image image) {
        for(MultimediaPreviewController display:controllers){
            display.setImage(image);
        }
    }

    public void addController(MultimediaPreviewController controller) {
        if(!isOnList(controller)) {
            controllers.add(controller);
        }
    }

    public void removeController(MultimediaPreviewController controller) {
        MultimediaPreviewController display = getControllerById(controller.getId());
        if(display!=null) {
            controllers.remove(display);
        }
    }

    private boolean isOnList(MultimediaPreviewController controller) {
        return getControllerById(controller.getId()) != null;
    }

    @Nullable
    private MultimediaPreviewController getControllerById(String id) {
        for(MultimediaPreviewController display: controllers){
            if(display.getId().equals(id)) {
                return display;
            }
        }
        return null;
    }

    private void reloadThread() {
        if(countdown!=null) {
            countdown.stop();
        }
        countdown = null;
        countdown = getThread();
    }

    private Thread getThread() {
        return new Thread(() -> {
            while (!pause) {
                try {
                    Thread.sleep(REFRESH_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDown();
            }
        });
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
        if(!pause) {
            reloadThread();
            countdown.start();
        }
    }

    public void setMonitor(Monitor monitor){
        this.monitor = monitor;
    }

    public Monitor getMonitor(){
        return monitor;
    }

    private static MultimediaPreviewer instance;
    private MultimediaPreviewer(){
        reloadThread();
    }
    public static MultimediaPreviewer getInstance(){
        if(instance==null) {
            instance = new MultimediaPreviewer();
        }
        return instance;
    }
}
