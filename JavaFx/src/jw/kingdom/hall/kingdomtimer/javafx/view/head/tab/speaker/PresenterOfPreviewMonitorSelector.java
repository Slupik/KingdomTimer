package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.speaker;

import javafx.scene.control.ChoiceBox;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.entity.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.entity.monitor.MonitorList;
import jw.kingdom.hall.kingdomtimer.javafx.control.monitor.MonitorChoiceBoxPresenter;
import jw.kingdom.hall.kingdomtimer.javafx.temp.MultimediaPreviewer;

/**
 * All rights reserved & copyright ©
 */
class PresenterOfPreviewMonitorSelector {

    private final Input input;

    PresenterOfPreviewMonitorSelector(Input input){
        this.input = input;
        init();
    }

    private void init() {
        new MonitorChoiceBoxPresenter(getMonitorList(), getBox()).addListener((last, now) ->{
            MultimediaPreviewer.getInstance().setMonitor(now);
            getConfig().setMultimediaScreen(now.getID());
        });
        setValueFromConfig();
    }

    private void setValueFromConfig() {
        getBox().setValue(getMonitorList().findById(getConfig().getMultimediaScreen()));
    }

    private ChoiceBox<Monitor> getBox() {
        return input.getBoxWithMonitorForSpeaker();
    }

    private Config getConfig() {
        return input.getConfig();
    }

    private MonitorList getMonitorList() {
        return input.getMonitorList();
    }

    interface Input {
        ChoiceBox<Monitor> getBoxWithMonitorForSpeaker();
        MonitorList getMonitorList();
        Config getConfig();
    }
}
