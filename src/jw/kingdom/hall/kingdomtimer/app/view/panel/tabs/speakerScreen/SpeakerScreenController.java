package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.speakerScreen;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.device.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorManager;
import jw.kingdom.hall.kingdomtimer.domain.countdown.gleam.GlobalGleamController;
import jw.kingdom.hall.kingdomtimer.javafx.custom.AdvancedTextField;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MultimediaPreviewer;
import jw.kingdom.hall.kingdomtimer.app.view.common.ControlledScreenImpl;
import jw.kingdom.hall.kingdomtimer.domain.utils.Randomizer;
import jw.kingdom.hall.kingdomtimer.app.view.viewer.ViewerWindow;
import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class SpeakerScreenController extends ControlledScreenImpl implements Initializable {

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
    public void initialize(URL location, ResourceBundle resources) {
        setupScreenSelectors();
        setupPreviewHidder();
        setupGleamController();
        setupHidingScreen();

        bindConfig();
        loadConfig();

        lastSavedInterval = Integer.parseInt(atfRefreshInterval.getSaveText());
    }

    private void setupHidingScreen() {
        cbVisibilitySpeakerScreen.setSelected(isVisible());
        cbVisibilitySpeakerScreen.selectedProperty().addListener(new ChangeListener<Boolean>() {
            private boolean ignore = false;

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(ignore) return;
                ignore = true;
                boolean success = ViewerWindow.getInstance().setVisibility(newValue);
                if(!success) {
                    cbVisibilitySpeakerScreen.setSelected(oldValue);
                }
                AppConfig.getInstance().setVisibilitySpeakerScreen(newValue);
                ignore = false;
            }
        });
        ViewerWindow.getInstance().addOnMonitorChangeListener(new ViewerWindow.Listener() {
            private String ID = Randomizer.randomStandardString(10);

            @Override
            public void onMonitorChange(Monitor monitor) {
                cbVisibilitySpeakerScreen.setSelected(isVisible());
            }

            @Override
            public String getId() {
                return ID;
            }
        });
    }

    private void bindConfig() {
        cbEnableGleaming.selectedProperty().addListener((observable, oldValue, newValue) ->
                AppConfig.getInstance().setEnabledGleaming(newValue));
        cbShowPreview.selectedProperty().addListener((observable, oldValue, newValue) ->
                AppConfig.getInstance().setEnabledShowMultimedia(newValue));
    }

    private void loadScreenFromConfig(ChoiceBox<Monitor> choiceBox, String screenID) {
        Monitor multiMonitor = getMonitorFromList(choiceBox.getItems(), screenID);
        choiceBox.setValue(multiMonitor);
    }

    private void loadConfig() {
        loadScreenFromConfig(cbMultimediaScreen, AppConfig.getInstance().getMultimediaScreen());
        loadScreenFromConfig(cbPreviewScreen, AppConfig.getInstance().getSpeakerScreen());

        cbEnableGleaming.setSelected(AppConfig.getInstance().isEnabledGleaming());
        cbShowPreview.setSelected(AppConfig.getInstance().isEnabledShowMultimedia());
        atfRefreshInterval.setText(
                String.valueOf(AppConfig.getInstance().getActualRefreshRate())
        );
    }

    private boolean isVisible() {
        if(ViewerWindow.getInstance().getMonitor()!=null) {
            return AppConfig.getInstance().isVisibleSpeakerScreen();
        }
        return false;
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
            AppConfig.getInstance().setMultimediaScreen(monitor);
        });
        if(cbMultimediaScreen.getItems().size()>2) {
            for(int i=0;i<cbMultimediaScreen.getItems().size();i++){
                Monitor monitor = cbMultimediaScreen.getItems().get(i);
                Monitor presentation = ViewerWindow.getInstance().getMonitor();
                if(!monitor.isMain() && (presentation==null || presentation.ID.equals(monitor.ID))) {
                    cbMultimediaScreen.setValue(monitor);
                    break;
                }
            }
        }

        ViewerWindow.getInstance().addOnMonitorChangeListener(new ViewerWindow.Listener() {
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
                    ViewerWindow.getInstance().setMonitor(monitor);
                    lastMonitor = monitor;
                    AppConfig.getInstance().setSpeakerScreen(monitor);
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
        atfRefreshInterval.setText(String.valueOf(AppConfig.getInstance().getDefaultRefreshRate()));
        saveInterval();
    }

    @FXML
    void loadInterval(ActionEvent event) {
        int current = Integer.parseInt(atfRefreshInterval.getSaveText());
        if(current<AppConfig.getInstance().getMinRefreshRate()) {
            RefreshRateDialogs.showTooLowValue();
            repairInterval();
        } else if(current<AppConfig.getInstance().getWarningRefreshRate()) {
            RefreshRateDialogs.showWarning(
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
        AppConfig.getInstance().setActualRefreshRate(current);
    }

    @Override
    protected Region getMainContainer() {
        return mainContainer;
    }
}
