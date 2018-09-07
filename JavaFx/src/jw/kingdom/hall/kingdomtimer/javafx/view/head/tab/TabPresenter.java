package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab;

import javafx.fxml.Initializable;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public abstract class TabPresenter implements TabPresentable, Initializable {
    private ScheduleController schedule;
    private Config config;

    @Override
    public Config getConfig() {
        return config;
    }

    @Override
    public void setConfig(Config config) {
        this.config = config;
        runPostCreateIfConditionsMet();
    }

    @Override
    public ScheduleController getSchedule() {
        return schedule;
    }

    @Override
    public void setSchedule(ScheduleController schedule) {
        this.schedule = schedule;
        runPostCreateIfConditionsMet();
    }

    private void runPostCreateIfConditionsMet() {
        if(schedule!=null && config!=null ) {
            onStart();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onCreate(location, resources);
    }

    public abstract void onStart();

    protected abstract void onCreate(URL location, ResourceBundle resources);
}
