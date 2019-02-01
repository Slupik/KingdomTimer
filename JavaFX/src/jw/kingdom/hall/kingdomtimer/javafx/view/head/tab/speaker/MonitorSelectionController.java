package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.speaker;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.domain.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.javafx.common.monitors.MonitorSelector;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.javafx.view.speaker.SpeakerWindow;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class MonitorSelectionController {

    private final Input input;

    //TODO improve action on second monitor plugout
    MonitorSelectionController(Input input) {
        this.input = input;
        loadConfig();
        setupScreenSelectors();
        autoSetupMultimediaScreen();

        getSpeakerWindow().setMonitor(getPreviewSelector().getSelected());
        getWindowData().getSpeakerPreviewController().setMonitor(getMultimediaSelector().getSelected());
    }

    private void loadConfig() {
        getMultimediaSelector().setSelection(getConfig().getMultimediaScreen());
        getPreviewSelector().setSelection(getConfig().getSpeakerScreen());
    }

    //TODO improve autoselect
    private void autoSetupMultimediaScreen() {
        if(getConfig().getMultimediaScreen()==null || getConfig().getMultimediaScreen().length()<1) {
            List<Monitor> connected = getWindowData().getMonitorsManager().getAll();
            for(Monitor monitor:connected) {
                if(!monitor.isPrimary() && !monitor.getId().equals(getPreviewSelector().getId())) {
                    getMultimediaSelector().setSelection(monitor.getId());
                    return;
                }
            }
        }
    }

    private void setupScreenSelectors() {
        getMultimediaSelector().addListener((newMonitor, oldMonitor) -> {
            getWindowData().getSpeakerPreviewController().setMonitor(newMonitor);
            getConfig().setMultimediaScreen(newMonitor.getId());
        });

        getPreviewSelector().addListener((newMonitor, oldMonitor) -> {
            if(newMonitor.isPrimary() && !SpeakerWindow.DEBUGGING_FORCE_SHOW_ON_SINGLE_MONITOR) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Nie możesz wyświetlić ekranu dla mówcy na swoim głównym ekranie.");

                alert.showAndWait();
                Platform.runLater(()-> getPreviewSelector().setSelection(oldMonitor));
            } else {
                getSpeakerWindow().setMonitor(newMonitor);
                getConfig().setSpeakerScreen(newMonitor.getId());
            }
        });
    }

    private MonitorSelector getMultimediaSelector() {
        return input.getMultimediaSelector();
    }

    private MonitorSelector getPreviewSelector() {
        return input.getPreviewSelector();
    }

    private Config getConfig() {
        return input.getConfig();
    }

    private SpeakerWindow getSpeakerWindow() {
        return input.getSpeakerWindow();
    }

    private WindowInput getWindowData() {
        return input.getWindowData();
    }

    interface Input {
        MonitorSelector getMultimediaSelector();
        MonitorSelector getPreviewSelector();
        Config getConfig();
        SpeakerWindow getSpeakerWindow();
        WindowInput getWindowData();
    }
}
