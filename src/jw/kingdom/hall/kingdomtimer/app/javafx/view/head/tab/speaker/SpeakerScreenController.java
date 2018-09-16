package jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.speaker;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.WindowType;
import jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.TabPresenter;
import jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker.SpeakerWindow;
import jw.kingdom.hall.kingdomtimer.device.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorManager;
import jw.kingdom.hall.kingdomtimer.domain.countdown.gleam.GlobalGleamController;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MultimediaPreviewer;
import jw.kingdom.hall.kingdomtimer.domain.utils.Randomizer;
import jw.kingdom.hall.kingdomtimer.javafx.custom.AdvancedTextField;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class SpeakerScreenController extends TabPresenter {

    @FXML
    VBox mainContainer;

    @FXML
    CheckBox cbShowPreview;

    @FXML
    CheckBox cbVisibilitySpeakerScreen;

    @FXML
    CheckBox cbEnableGleaming;

    @FXML
    AdvancedTextField atfRefreshInterval;

    @FXML
    ChoiceBox<Monitor> cbMultimediaScreen;

    @FXML
    ChoiceBox<Monitor> cbPreviewScreen;

    private int lastSavedInterval = -1;

    @Override
    public void onSetup() {
        super.onSetup();
        setupScreenSelectors();
        setupPreviewHidder();
        setupGleamController();
        setupHidingController();

        bindConfig();
        loadConfig();

        lastSavedInterval = Integer.parseInt(atfRefreshInterval.getSaveText());
    }

    private void setupHidingController() {
        new SpeakerScreenVisibilityController(
                getSpeakerWindow(),
                getConfig(),
                cbVisibilitySpeakerScreen);
    }

    private void bindConfig() {
        cbEnableGleaming.selectedProperty().addListener((observable, oldValue, newValue) ->
                getConfig().setEnabledGleaming(newValue));
        cbShowPreview.selectedProperty().addListener((observable, oldValue, newValue) ->
                getConfig().setEnabledShowMultimedia(newValue));
    }

    private void loadConfig() {
        loadScreenFromConfig(cbMultimediaScreen, getConfig().getMultimediaScreen());
        loadScreenFromConfig(cbPreviewScreen, getConfig().getSpeakerScreen());
        autoSetupMultimediaScreen();

        cbEnableGleaming.setSelected(getConfig().isEnabledGleaming());
        cbShowPreview.setSelected(getConfig().isEnabledShowMultimedia());
        atfRefreshInterval.setText(
                String.valueOf(getConfig().getActualRefreshRate())
        );
    }

    private void autoSetupMultimediaScreen() {
        if(getConfig().getMultimediaScreen()==null || getConfig().getMultimediaScreen().length()<1) {
            for(Monitor monitor:cbMultimediaScreen.getItems()) {
                if(!monitor.isMain() && cbPreviewScreen.getValue()!=null) {
                    cbMultimediaScreen.setValue(monitor);
                    return;
                }
            }
        }
    }

    private void loadScreenFromConfig(ChoiceBox<Monitor> choiceBox, String screenID) {
        setMonitorFromConfig(choiceBox, screenID);
        choiceBox.getItems().addListener((ListChangeListener<Monitor>) c -> Platform.runLater(()->{
            if(choiceBox.getValue()==null) {
                setMonitorFromConfig(choiceBox, screenID);
                autoSetupMultimediaScreen();
            }
        }));
    }

    private void setMonitorFromConfig(ChoiceBox<Monitor> choiceBox, String monitorId) {
        Monitor multiMonitor = getMonitorFromList(choiceBox.getItems(), monitorId);
        choiceBox.setValue(multiMonitor);
    }

    private void setupGleamController() {
        GlobalGleamController.getInstance().setEnable(cbEnableGleaming.isSelected());
        cbEnableGleaming.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue!=newValue) {
                GlobalGleamController.getInstance().setEnable(newValue);
            }
        });
    }

    private void setupPreviewHidder() {
        cbShowPreview.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue!=newValue) {
                MultimediaPreviewer.getInstance().showPreviews(newValue);
            }
        });
    }

    private void setupScreenSelectors() {
        cbMultimediaScreen.setItems(MonitorManager.monitors);
        cbPreviewScreen.setItems(MonitorManager.monitors);

        cbMultimediaScreen.getSelectionModel().selectedIndexProperty().addListener((observableValue, oldValue, newValue) -> {
            Monitor monitor = cbPreviewScreen.getItems().get((Integer) newValue);
            MultimediaPreviewer.getInstance().setMonitor(monitor);
            getConfig().setMultimediaScreen(monitor);
        });
        if(cbMultimediaScreen.getItems().size()>2) {
            for(int i=0;i<cbMultimediaScreen.getItems().size();i++){
                Monitor monitor = cbMultimediaScreen.getItems().get(i);
                Monitor presentation = getSpeakerWindow().getMonitor();
                if(!monitor.isMain() && (presentation==null || presentation.ID.equals(monitor.ID))) {
                    cbMultimediaScreen.setValue(monitor);
                    break;
                }
            }
        }

        getSpeakerWindow().addOnMonitorChangeListener(new SpeakerWindow.Listener() {
            private String ID = Randomizer.randomStandardString(10);
            private boolean ignore = false;

            @Override
            public void onMonitorChange(Monitor monitor) {
                if(monitor==null) {
                    return;
                }
                if(ignore) return;
                ignore = true;//fire only once
                cbPreviewScreen.setValue(getMonitorFromList(cbPreviewScreen.getItems(), monitor.ID));
            }

            @Override
            public String getId() {
                return ID;
            }
        });
        cbPreviewScreen.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            private Monitor lastMonitor;

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if(newValue.doubleValue()<0){
                    return;
                }
                Monitor monitor = cbPreviewScreen.getItems().get((Integer) newValue);
                    if(null!=lastMonitor && lastMonitor.ID.equals(monitor.ID)){
                    return;
                }
                if(monitor.isMain()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText("Nie możesz wyświetlić ekranu dla mówcy na swoim głównym ekranie.");

                    alert.showAndWait();
                    returnToLastValue(oldValue);
                } else {
                    getSpeakerWindow().setMonitor(monitor);
                    lastMonitor = monitor;
                    getConfig().setSpeakerScreen(monitor);
                }
            }

            private void returnToLastValue(Number oldValue) {
                Platform.runLater(()->{
                    if(oldValue.doubleValue()<0) {
                        cbPreviewScreen.setValue(null);
                    } else {
                        cbPreviewScreen.setValue(cbPreviewScreen.getItems().get((Integer) oldValue));
                    }
                });
            }
        });
    }

    @Nullable
    private Monitor getMonitorFromList(List<Monitor> list, String id) {
        for(Monitor monitor:list){
            if (monitor.ID.equals(id)) {
                return monitor;
            }
        }
        return null;
    }

    @FXML
    void loadDefaultInterval(ActionEvent event) {
        atfRefreshInterval.setText(String.valueOf(getConfig().getDefaultRefreshRate()));
        saveInterval();
    }

    @FXML
    void loadInterval(ActionEvent event) {
        int current = Integer.parseInt(atfRefreshInterval.getSaveText());
        if(current<getConfig().getMinRefreshRate()) {
            RefreshRateDialogs.showTooLowValue(
                    getConfig().getMinRefreshRate()
            );
            repairInterval();
        } else if(current<getConfig().getWarningRefreshRate()) {
            RefreshRateDialogs.showWarning(
                    getConfig().getWarningRefreshRate(),
                    this::saveInterval,
                    this::repairInterval
            );
        } else {
            saveInterval();
        }
    }

    private void repairInterval() {
        atfRefreshInterval.setText(String.valueOf(lastSavedInterval));
    }

    private void saveInterval() {
        int current = Integer.parseInt(atfRefreshInterval.getSaveText());
        lastSavedInterval = current;
        MultimediaPreviewer.getInstance().setRefreshInterval(current);
        getConfig().setActualRefreshRate(current);
    }

    private SpeakerWindow getSpeakerWindow() {
        return (SpeakerWindow) getWindowsContainer().getAppWindow(WindowType.SPEAKER);
    }
}
