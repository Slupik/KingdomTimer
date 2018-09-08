package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.speaker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.entity.monitor.Monitor;
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
public class TabSpeakerPresenter extends TabPresenter {

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
        //TODO move those boxes to additional controller
        new MonitorChoiceBoxPresenter(getWindowData().getMonitorList(), cbMultimediaScreen).addListener((last, now) ->{
            MultimediaPreviewer.getInstance().setMonitor(now);
            getWindowData().getConfig().setMultimediaScreen(now.getID());
        });
        cbMultimediaScreen.setValue(getWindowData().getMonitorList().findById(getWindowData().getConfig().getMultimediaScreen()));
        new MonitorChoiceBoxPresenter(getWindowData().getMonitorList(), cbPreviewScreen).addListener((last, now) -> {
            getWindowData().getWindowsContainer().getAppWindow(WindowType.SPEAKER).getStage().setX(now.getBounds().getX());
            getWindowData().getWindowsContainer().getAppWindow(WindowType.SPEAKER).getStage().setY(now.getBounds().getY());
            getWindowData().getConfig().setSpeakerScreen(now.getID());
        });
        cbPreviewScreen.setValue(getWindowData().getMonitorList().findById(getWindowData().getConfig().getSpeakerScreen()));
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
}
