package jw.kingdom.hall.kingdomtimer.javafx.view.speaker;

import javafx.application.Platform;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.entity.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.WindowInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static jw.kingdom.hall.kingdomtimer.javafx.view.speaker.SpeakerWindow.Screens.MAIN;

/**
 * All rights reserved & copyright ©
 */
public class SpeakerWindow extends AppWindow {
    private List<Listener> listeners = new ArrayList<>();
    private Monitor lastMonitor = null;
    private Monitor actualDevice = null;

    public SpeakerWindow(Stage stage, WindowInput input) {
        super(stage, input);
    }

    @Override
    protected void loadScreens() {
        viewManager.loadScreen(MAIN.name, MAIN.path);
    }

    @Override
    protected void setMainView() {
        viewManager.setScreen(MAIN.name);
    }

    @Override
    protected void onPreInit() {

    }

    @Override
    protected void onPreShow() {

    }

    @Override
    protected void onPostShow() {
        setMonitor(input.getMonitorList().findById(input.getConfig().getSpeakerScreen()));
        if(actualDevice==null) {
//            getStage().hide();
        }
    }

    public boolean setVisibility(Boolean isVisible) {
        if(actualDevice!=null)  {
            if(isVisible) {
                getStage().show();
            } else {
                getStage().hide();
            }
            return true;
        }
        return false;
    }

    /**
     * @return information about success
     */
    public boolean setMonitor(Monitor monitor) {
        if((monitor!=null && lastMonitor!=null && Objects.equals(monitor.getID(), lastMonitor.getID())) ||
                (monitor!=null && monitor.isMain())){
            return false;
        }
        actualDevice = monitor;
        notifyMonitorChange(monitor);
        Platform.runLater(()->{
            if(monitor==null){
                getStage().hide();
                return;
            }

            setVisibility(input.getConfig().isVisibleSpeakerScreen());

            getStage().setWidth(monitor.getBounds().getWidth());
            getStage().setHeight(monitor.getBounds().getHeight());

            getStage().setX(monitor.getBounds().getX());
            getStage().setY(monitor.getBounds().getY());

            stage.setMaximized(true);
        });
        return true;
    }

    private void notifyMonitorChange(Monitor monitor) {
        for(Listener listener:listeners) {
            listener.onMonitorChange(monitor);
        }
    }

    public Monitor getMonitor() {
        return lastMonitor;
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public enum Screens {
        MAIN("main", "/layout/window/speaker/main.fxml"),
        ;

        public final String name;
        public final String path;

        Screens(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }

    public interface Listener {
        void onMonitorChange(Monitor monitor);
    }
}
