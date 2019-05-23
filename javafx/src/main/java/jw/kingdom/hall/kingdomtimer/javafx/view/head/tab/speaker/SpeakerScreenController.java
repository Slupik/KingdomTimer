package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.speaker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.domain.countdown.gleam.GlobalGleamController;
import jw.kingdom.hall.kingdomtimer.javafx.common.monitors.MonitorSelector;
import jw.kingdom.hall.kingdomtimer.javafx.custom.AdvancedTextField;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.WindowType;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.TabPresenter;
import jw.kingdom.hall.kingdomtimer.javafx.view.speaker.SpeakerWindow;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class SpeakerScreenController extends TabPresenter implements MonitorSelectionController.Input {

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
    VBox vbMultimediaScreen;

    @FXML
    VBox vbPreviewScreen;

    private int lastSavedInterval = -1;
    private MonitorSelector mSelectorForMultimedia;
    private MonitorSelector mSelectorForPreview;

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

    private void setupScreenSelectors() {
        mSelectorForPreview = new MonitorSelector(getWindowData().getMonitorsManager());
        vbPreviewScreen.getChildren().add(mSelectorForPreview);

        mSelectorForMultimedia = new MonitorSelector(getWindowData().getMonitorsManager());
        vbMultimediaScreen.getChildren().add(mSelectorForMultimedia);

        new MonitorSelectionController(this);
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
        cbEnableGleaming.setSelected(getConfig().isEnabledGleaming());
        cbShowPreview.setSelected(getConfig().isEnabledShowMultimedia());
        atfRefreshInterval.setText(
                String.valueOf(getConfig().getActualRefreshRate())
        );
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
                getWindowData().getSpeakerPreviewController().forceChangeDisplaysVisibility(newValue);
            }
        });
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
        getWindowData().getSpeakerPreviewController().setRefreshInterval(current);
        getConfig().setActualRefreshRate(current);
    }

    @Override
    public SpeakerWindow getSpeakerWindow() {
        return (SpeakerWindow) getWindowsContainer().getAppWindow(WindowType.SPEAKER);
    }

    @Override
    public MonitorSelector getMultimediaSelector() {
        return mSelectorForMultimedia;
    }

    @Override
    public MonitorSelector getPreviewSelector() {
        return mSelectorForPreview;
    }
}
