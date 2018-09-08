package jw.kingdom.hall.kingdomtimer.javafx.view.speaker;

import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.entity.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.WindowInput;

import static jw.kingdom.hall.kingdomtimer.javafx.view.speaker.SpeakerWindow.Screens.MAIN;

/**
 * All rights reserved & copyright ©
 */
public class SpeakerWindow extends AppWindow {
    private Monitor lastMonitor = null;

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
    }

    /**
     * @return information about success
     */
    public boolean setMonitor(Monitor monitor) {
        if(monitor==null) {
            getStage().hide();
            return true;
        } else if(lastMonitor==null || !lastMonitor.getID().equals(monitor.getID())){
            getStage().setX(monitor.getBounds().getX());
            getStage().setY(monitor.getBounds().getY());
            getStage().setWidth(monitor.getBounds().getWidth());
            getStage().setHeight(monitor.getBounds().getHeight());
            getStage().show();
            return true;
        }
        return false;
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
}
