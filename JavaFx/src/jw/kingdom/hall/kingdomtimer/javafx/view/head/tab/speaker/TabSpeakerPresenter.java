package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.speaker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.entity.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.entity.monitor.MonitorList;
import jw.kingdom.hall.kingdomtimer.javafx.control.monitor.MonitorChoiceBoxPresenter;
import jw.kingdom.hall.kingdomtimer.javafx.control.preview.MultimediaPreviewController;
import jw.kingdom.hall.kingdomtimer.javafx.custom.AdvancedTextField;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.WindowType;
import jw.kingdom.hall.kingdomtimer.javafx.temp.MultimediaPreviewer;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.TabPresenter;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class TabSpeakerPresenter extends TabPresenter implements PresenterOfPreviewMonitorSelector.Input,
        PresenterOfMultimediaMonitorSelector.Input{

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

    @Override
    public void onStart() {
        new PresenterOfMultimediaMonitorSelector(this);
        new PresenterOfPreviewMonitorSelector(this);
        MultimediaPreviewer.getInstance().setRefreshInterval(500);
        MultimediaPreviewer.getInstance().setPause(false);
        MultimediaPreviewer.getInstance().showPreviews(true);
    }

    @Override
    protected void onCreate(URL location, ResourceBundle resources) {

    }

    public void loadInterval(ActionEvent actionEvent) {

    }

    public void loadDefaultInterval(ActionEvent actionEvent) {

    }

    @Override
    public ChoiceBox<Monitor> getBoxWithMonitorForPreview() {
        return cbMultimediaScreen;
    }

    @Override
    public ChoiceBox<Monitor> getBoxWithMonitorForSpeaker() {
        return cbPreviewScreen;
    }

    @Override
    public MonitorList getMonitorList() {
        return getWindowData().getMonitorList();
    }

    @Override
    public Config getConfig() {
        return getWindowData().getConfig();
    }
}
