package jw.kingdom.hall.kingdomtimer.app.view.handy;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import jw.kingdom.hall.kingdomtimer.app.view.common.AnimatedZoomOperator;
import jw.kingdom.hall.kingdomtimer.app.view.common.ControlledScreenImpl;
import jw.kingdom.hall.kingdomtimer.app.view.common.controller.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.app.view.common.custom.sps.StartPauseStopView;
import jw.kingdom.hall.kingdomtimer.domain.time.countdown.TimerCountdown;
import jw.kingdom.hall.kingdomtimer.domain.time.countdown.TimerCountdownListener;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.time.schedule.MeetingSchedule;
import jw.kingdom.hall.kingdomtimer.domain.time.schedule.NotEnoughTasksException;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class HandyPanelController extends ControlledScreenImpl implements Initializable, StartPauseStopView.Listener, StartPauseStopView.Controller {

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
    public void initialize(URL location, ResourceBundle resources) {
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
                HandyWindow.getInstance().getStage().setWidth(
                        HandyWindow.getInstance().getStage().getWidth()+diff
                );
            });
        });
    }

    private void initTimeController() {
        timeDisplay = new TimeDisplayController(lblTime);
        timeDisplay.setTime(0);
        getTimer().addController(timeDisplay);
    }

    private void initSps() {
        spsView = new StartPauseStopView();
        hbTimeControlsContainer.getChildren().add(spsView);
        spsView.addListener(this);
        spsView.setController(this);

        TimerCountdown.getInstance().addListener(new TimerCountdownListener() {
            @Override
            public void onStart(MeetingTask task) {
                super.onStart(task);
                spsView.setupForStart();
                lblNow.setText(task.getName());
            }

            @Override
            public void onPause() {
                super.onPause();
                spsView.setupForPause();
            }

            @Override
            public void onResume() {
                super.onPause();
                spsView.setupForUnPause();
            }

            @Override
            public void onStop() {
                super.onStop();
                spsView.setupForStop();
                lblNow.setText("");
            }
        });
    }

    @FXML
    void minifyAction(ActionEvent event) {
        HandyWindow.getInstance().getStage().setIconified(true);
    }

    @Override
    protected Region getMainContainer() {
        return mainContainer;
    }

    private TimerCountdown getTimer() {
        return TimerCountdown.getInstance();
    }

    @Override
    public void onStart() {
        try {
            MeetingTask task = MeetingSchedule.getInstance().bringOutFirstTask();
            getTimer().start(task);
        } catch (NotEnoughTasksException ignore) {}
    }

    @Override
    public void onPause() {
        getTimer().pause();
    }

    @Override
    public void onUnPause() {
        getTimer().resume();
    }

    @Override
    public void onStop() {
        getTimer().stop();
    }

    @Override
    public boolean isToExecuteSPSAction(StartPauseStopView.ActionType type) {
        if(type==StartPauseStopView.ActionType.START) {
            return MeetingSchedule.getInstance().getList().size()!=0;
        }
        return true;
    }
}
