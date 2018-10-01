package jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab;

import javafx.fxml.Initializable;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.container.WindowsContainer;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.RecordControl;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public abstract class TabPresenter implements TabPresentable, Initializable {
    private WindowsContainer windowsContainer;
    private WindowInput data;

    @Override
    public void setWindowsContainer(WindowsContainer windowsContainer) {
        this.windowsContainer = windowsContainer;
        runPostCreateIfConditionsMet();
    }

    @Override
    public WindowsContainer getWindowsContainer() {
        return windowsContainer;
    }

    @Override
    public void setWindowData(WindowInput input) {
        this.data = input;
        runPostCreateIfConditionsMet();
    }

    @Override
    public WindowInput getWindowData() {
        return data;
    }

    private void runPostCreateIfConditionsMet() {
        if(data!=null && windowsContainer!=null) {
            onSetup();
        }
    }

    @Override
    public final void initialize(URL location, ResourceBundle resources) {
        onCreate(location, resources);
    }

    protected void onCreate(URL location, ResourceBundle resources) {

    }

    public void onSetup() {

    }

    public final AppConfig getConfig() {
        return getWindowData().getConfig();
    }

    public final RecordControl getRecorder(){
        return getWindowData().getRecorder();
    }

    public final Schedule getSchedule(){
        return getWindowData().getSchedule();
    }

    public final Countdown getCountdown(){
        return getWindowData().getCountdown();
    }

    public final TimeController getTimer(){
        return getWindowData().getTimeController();
    }
}
