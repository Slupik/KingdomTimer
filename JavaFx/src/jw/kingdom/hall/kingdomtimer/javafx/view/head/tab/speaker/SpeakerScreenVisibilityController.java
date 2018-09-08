package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.speaker;

import javafx.scene.control.CheckBox;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.WindowType;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.container.WindowsContainer;
import jw.kingdom.hall.kingdomtimer.javafx.view.speaker.SpeakerWindow;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class SpeakerScreenVisibilityController {
    private final Input input;
    private boolean ignore = false;

    SpeakerScreenVisibilityController(Input input) {
        this.input = input;
        init();
    }

    private void init() {
        setupWithoutSaving();
        getBox().selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(ignore) return;
            ignore = true;
            boolean success = getSpeakerWindow().setVisibility(newValue);
            if(!success) {
                getBox().setSelected(oldValue);
                return;
            }
            getConfig().setVisibilitySpeakerScreen(newValue);
            ignore = false;
        });
        getSpeakerWindow().addListener(monitor -> setupWithoutSaving());
    }

    private void setupWithoutSaving() {
        ignore = true;
        if(getSpeakerWindow().getMonitor()!=null) {
            getBox().setDisable(false);
            getBox().setSelected(getConfig().isVisibleSpeakerScreen());
        } else {
            getBox().setDisable(true);
            getBox().setSelected(false);
        }
        ignore = false;
    }

    private SpeakerWindow getSpeakerWindow() {
        return (SpeakerWindow) getWindowsContainer().getAppWindow(WindowType.SPEAKER);
    }

    private CheckBox getBox() {
        return input.getSpeakerVisibilityBox();
    }

    private WindowsContainer getWindowsContainer() {
        return input.getWindowsContainer();
    }

    private Config getConfig() {
        return input.getConfig();
    }

    interface Input {
        CheckBox getSpeakerVisibilityBox();
        Config getConfig();
        WindowsContainer getWindowsContainer();
    }
}
