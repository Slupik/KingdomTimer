package jw.kingdom.hall.kingdomtimer.app.javafx.view.widget;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.time.display.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.app.javafx.common.sps.StartPauseStopView;
import jw.kingdom.hall.kingdomtimer.app.javafx.common.zoom.AnimatedZoomOperator;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.screen.ControlledScreenBase;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.WindowType;
import jw.kingdom.hall.kingdomtimer.app.javafx.utils.PlatformUtils;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeListenerProxy;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class HandyPanelController extends ControlledScreenBase implements StartPauseStopView.Listener, StartPauseStopView.Controller {

    @FXML
    private HBox mainContainer;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblNow;

    @FXML
    private HBox hbTimeControlsContainer;
    private StartPauseStopView spsView;
    private TimeDisplayController timeDisplay;
    private double lastSize = -1;

    @Override
    protected void onSetup() {
        super.onSetup();
        initSps();
        initTimeController();
        setupZoom();
        setupAutoChangingSize();

        lblNow.setText("");
    }

    private void setupZoom() {
        AnimatedZoomOperator zoomOperator = new AnimatedZoomOperator();
        getMainContainer().setOnScroll(event -> {
            double zoomFactor = 1.1;
            if (event.getDeltaY() <= 0) {
                // zoom out
                zoomFactor = 1 / zoomFactor;
            }
            zoomOperator.zoom(getMainContainer(), zoomFactor);
        });
    }

    private void setupAutoChangingSize() {
        Platform.runLater(()->{
            lastSize = lblTime.widthProperty().doubleValue();
            lblTime.widthProperty().addListener((observable, oldValue, newValue) -> {
                double diff = newValue.doubleValue()-lastSize;
                lastSize = newValue.doubleValue();
                getHandyWindow().getStage().setWidth(
                        getHandyWindow().getStage().getWidth()+diff
                );
            });
        });
    }

    private void initTimeController() {
        timeDisplay = new TimeDisplayController(lblTime);
        getTimer().addDisplay(timeDisplay);
    }

    private void initSps() {
        spsView = new StartPauseStopView();
        hbTimeControlsContainer.getChildren().add(spsView);
        spsView.addListener(this);
        spsView.setController(this);

        getTimer().addListener(new TimeListenerProxy() {
            @Override
            public void onStart(MeetingTask task) {
                super.onStart(task);
                PlatformUtils.runOnUiThread(()->{
                    spsView.setupForStart();
                    lblNow.setText(task.getName());
                });
            }

            @Override
            public void onPause() {
                super.onPause();
                PlatformUtils.runOnUiThread(()->{
                    spsView.setupForPause();
                });
            }

            @Override
            public void onResume() {
                super.onPause();
                PlatformUtils.runOnUiThread(()->{
                    spsView.setupForUnPause();
                });
            }

            @Override
            public void onStop() {
                super.onStop();
                PlatformUtils.runOnUiThread(()->{
                    spsView.setupForStop();
                    lblNow.setText("");
                });
            }
        });
    }

    @FXML
    void minifyAction(ActionEvent event) {
        getHandyWindow().getStage().setIconified(true);
    }

    @Override
    protected Region getMainContainer() {
        return mainContainer;
    }

    @Override
    public void onStart() {
        getTimer().startNext();
    }

    @Override
    public void onPause() {
        getTimer().pause();
    }

    @Override
    public void onResume() {
        getTimer().resume();
    }

    @Override
    public void onStop() {
        getTimer().stop();
    }

    @Override
    public boolean isToExecuteSPSAction(StartPauseStopView.ActionType type) {
        if(type==StartPauseStopView.ActionType.START) {
            return getTimer().getList().size()!=0;
        }
        return true;
    }

    private HandyWindow getHandyWindow() {
        return (HandyWindow) getWindowData().getWindowsContainer().getAppWindow(WindowType.WIDGET);
    }
}
