package jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.speaker;

import javafx.scene.control.CheckBox;
import jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker.SpeakerWindow;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.device.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.domain.utils.Randomizer;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class SpeakerScreenVisibilityController {
    private final SpeakerWindow window;
    private final Config config;
    private final CheckBox box;
    private boolean ignore = false;

    SpeakerScreenVisibilityController(SpeakerWindow window, Config config, CheckBox box) {
        this.window = window;
        this.config = config;
        this.box = box;
        init();
    }

    private void init() {
        setupWithoutSaving();
        box.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(ignore) return;
            ignore = true;
            boolean success = getWindow().setVisibility(newValue);
            if(!success) {
                box.setSelected(oldValue);
                return;
            }
            getConfig().setVisibilitySpeakerScreen(newValue);
            ignore = false;
        });
        getWindow().addOnMonitorChangeListener(new SpeakerWindow.Listener() {
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
        if(getWindow().getMonitor()!=null) {
            box.setDisable(false);
            box.setSelected(getConfig().isVisibleSpeakerScreen());
        } else {
            box.setDisable(true);
            box.setSelected(false);
        }
        ignore = false;
    }

    private Config getConfig() {
        return config;
    }

    private SpeakerWindow getWindow() {
        return window;
    }
}
