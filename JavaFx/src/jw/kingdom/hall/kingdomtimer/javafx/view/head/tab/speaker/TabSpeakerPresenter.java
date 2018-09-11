package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.speaker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.entity.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.entity.monitor.MonitorList;
import jw.kingdom.hall.kingdomtimer.entity.time.gleam.GleamController;
import jw.kingdom.hall.kingdomtimer.entity.time.gleam.GleamSwitcher;
import jw.kingdom.hall.kingdomtimer.javafx.control.gleam.GleamControllerImpl;
import jw.kingdom.hall.kingdomtimer.javafx.custom.AdvancedTextField;
import jw.kingdom.hall.kingdomtimer.javafx.temp.MultimediaPreviewer;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.TabPresenter;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class TabSpeakerPresenter extends TabPresenter implements PresenterOfMultimediaMonitorSelector.Input,
        PresenterOfSpeakerMonitorSelector.Input, ControllerOfIntervalLoading.Input,
        SpeakerScreenVisibilityController.Input, MultimediaPreviewBoxController.Input,
        GleamSwitchController.Input {

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

    private ControllerOfIntervalLoading intervalLoading;

    @Override
    public void onStart() {
        new PresenterOfSpeakerMonitorSelector(this);
        new PresenterOfMultimediaMonitorSelector(this);
        new SpeakerScreenVisibilityController(this);
        new MultimediaPreviewBoxController(this);
        new GleamSwitchController(this);
        intervalLoading = new ControllerOfIntervalLoading(this);
    }

    @Override
    protected void onCreate(URL location, ResourceBundle resources) {

    }

    public void loadInterval(ActionEvent actionEvent) {
        intervalLoading.load();
    }

    public void loadDefaultInterval(ActionEvent actionEvent) {
        intervalLoading.loadDefault();
    }

    @Override
    public ChoiceBox<Monitor> getBoxWithMonitorForSpeaker() {
        return cbPreviewScreen;
    }

    @Override
    public ChoiceBox<Monitor> getBoxWithMultimediaMonitor() {
        return cbMultimediaScreen;
    }

    @Override
    public MonitorList getMonitorList() {
        return getWindowData().getMonitorList();
    }

    @Override
    public CheckBox getSpeakerVisibilityBox() {
        return cbVisibilitySpeakerScreen;
    }

    @Override
    public Config getConfig() {
        return getWindowData().getConfig();
    }

    @Override
    public CheckBox getBoxForGleam() {
        return cbEnableGleaming;
    }

    @Override
    public GleamSwitcher getGleamSwitcher() {
        return getWindowData().getGleamSwitcher();
    }

    @Override
    public CheckBox getBoxForPreviewVisibility() {
        return cbShowPreview;
    }

    @Override
    public AdvancedTextField getField() {
        return atfRefreshInterval;
    }
}
