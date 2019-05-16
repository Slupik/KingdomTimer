package jw.kingdom.hall.kingdomtimer.domain.multimedia;

import javafx.scene.image.Image;
import jw.kingdom.hall.kingdomtimer.domain.monitor.Monitor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class MonitorPreviewControllerImpl implements MonitorPreviewController {
    private Thread countdown;
    private boolean pause = true;
    private boolean deadPause = false;//pause because nothing shows change
    private int refreshInterval = 500;
    private ExecutorService executor = Executors.newFixedThreadPool(5);

    private MonitorScreenshotMaker screenshotMaker;
    private Monitor monitor;
    private List<MonitorViewDisplay> displays = new ArrayList<>();
    private MonitorViewDisplay.Listener displayListener = this::handleShowingChange;

    public MonitorPreviewControllerImpl(MonitorScreenshotMaker maker){
        setScreenshotMaker(maker);
    }

    @Override
    public void setRefreshInterval(int interval) {
        refreshInterval = interval;
    }

    @Override
    public boolean isWorking() {
        return !pause && !deadPause;
    }

    @Override
    public boolean isPause() {
        return pause;
    }

    @Override
    public void setPause(boolean pause) {
        this.pause = pause;
        safeReloadThread();
    }

    @Override
    public void setMonitor(@NotNull Monitor monitor){
        this.monitor = monitor;
        Rectangle areaToMakeSS = new Rectangle();
        areaToMakeSS.setBounds(
                ((int) monitor.getX()),
                ((int) monitor.getY()),
                ((int) monitor.getWidth()),
                ((int) monitor.getHeight())
        );
        screenshotMaker.setArea(areaToMakeSS);
    }

    @Override
    public Monitor getMonitor(){
        return monitor;
    }

    @Override
    public void setScreenshotMaker(@NotNull MonitorScreenshotMaker maker) {
        screenshotMaker = maker;
        safeReloadThread();
    }

    @Override
    public MonitorScreenshotMaker getScreenshotMaker() {
        return screenshotMaker;
    }

    @Override
    public void addDisplay(@NotNull MonitorViewDisplay display) {
        displays.add(display);
        handleShowingChange(display.isShowing());
        display.addListener(displayListener);
    }

    @Override
    public void removeDisplay(@NotNull MonitorViewDisplay display) {
        displays.remove(display);
        display.removeListener(displayListener);
    }

    @Override
    public void forceChangeDisplaysVisibility(boolean isShowing) {
        for(MonitorViewDisplay display:displays) {
            display.forceShowing(isShowing);
        }
    }

    private void makeSS() {
        if(!pause && !deadPause) {
            executor.submit(() -> {
                Image image = screenshotMaker.getFxImage();
                for(MonitorViewDisplay display:displays) {
                    display.display(image);
                }
            });
        }
    }

    private void safeReloadThread() {
        resetThread();
        startLoopIfWorking();
    }

    private void startLoopIfWorking() {
        if (isWorking()) {
            countdown.start();
        }
    }

    @SuppressWarnings("deprecation")
    private void resetThread() {
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

    private int numberOfShowing;
    private void handleShowingChange(boolean isShowing) {
        if(isShowing){
            numberOfShowing++;
        } else {
            numberOfShowing--;
        }
        deadPause = numberOfShowing == 0;
    }
}
