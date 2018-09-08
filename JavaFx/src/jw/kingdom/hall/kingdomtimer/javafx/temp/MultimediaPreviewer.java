package jw.kingdom.hall.kingdomtimer.javafx.temp;

import com.sun.istack.internal.Nullable;
import javafx.scene.image.Image;
import jw.kingdom.hall.kingdomtimer.javafx.control.preview.MultimediaPreviewController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class MultimediaPreviewer {
    private List<MultimediaPreviewController> controllers = new ArrayList<>();
    private Thread countdown;
    private boolean pause = true;
    private boolean deadPause = false;//pause because nothing showing changes
//    private Monitor monitor;
    private int refreshInterval = 500;
    private ExecutorService executor = Executors.newFixedThreadPool(5);

    public void setRefreshInterval(int interval) {
        refreshInterval = interval;
    }

    //TODO repair this
    private void makeSS() {
        if(!pause && !deadPause) {
            executor.submit(() -> {
//                Image image = getMonitorScreen();
//                notifyControllers(image);
            });
        }
    }

//    @Nullable
//    private Image getMonitorScreen() {
//        if(monitor!=null) {
//            try {
//                Rectangle areaToMakeSS = monitor.getDefaultConfiguration().getBounds();
//                return SwingFXUtils.toFXImage(ScreenShotMaker.getSS(areaToMakeSS), null);
//            } catch (AWTException | IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

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

    @SuppressWarnings("deprecation")
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
                    Thread.sleep(refreshInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                makeSS();
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

//    public void setMonitor(Monitor monitor){
//        this.monitor = monitor;
//    }
//
//    public Monitor getMonitor(){
//        return monitor;
//    }

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

    public void showPreviews(boolean value) {
        for(MultimediaPreviewController controller:controllers) {
            controller.showPreview(value);
        }
        improvePerformance();
    }

    private void improvePerformance() {
        boolean shouldRefresh = false;
        for(MultimediaPreviewController controller:controllers) {
            if(controller.isShowing()) {
                shouldRefresh = true;
            }
        }
        deadPause = !shouldRefresh;
    }
}
