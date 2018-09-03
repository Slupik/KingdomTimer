package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.speakerScreen;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import jw.kingdom.hall.kingdomtimer.app.view.viewer.ViewerWindow;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.device.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.domain.utils.Randomizer;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class SpeakerScreenVisibilityController {
    private final CheckBox box;
    private boolean ignore = false;

    SpeakerScreenVisibilityController(CheckBox box) {
        this.box = box;
        init();
    }

    private void init() {
        setupWithoutSaving();
        box.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(ignore) return;
            ignore = true;
            boolean success = ViewerWindow.getInstance().setVisibility(newValue);
            if(!success) {
                box.setSelected(oldValue);
                return;
            }
            AppConfig.getInstance().setVisibilitySpeakerScreen(newValue);
            ignore = false;
        });
        ViewerWindow.getInstance().addOnMonitorChangeListener(new ViewerWindow.Listener() {
            private String ID = Randomizer.randomStandardString(10);

            @Override
            public void onMonitorChange(Monitor monitor) {
                setupWithoutSaving();
            }

            @Override
            public String getId() {
                return ID;
            }
        });
    }

    private void setupWithoutSaving() {
        ignore = true;
        if(ViewerWindow.getInstance().getMonitor()!=null) {
            box.setDisable(false);
            box.setSelected(AppConfig.getInstance().isVisibleSpeakerScreen());
        } else {
            box.setDisable(true);
            box.setSelected(false);
        }
        ignore = false;
    }
}
