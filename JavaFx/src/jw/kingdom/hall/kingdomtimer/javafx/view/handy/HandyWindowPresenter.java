package jw.kingdom.hall.kingdomtimer.javafx.view.handy;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownListener;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.javafx.control.sps.SpsControllerForTime;
import jw.kingdom.hall.kingdomtimer.javafx.control.time.display.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.screen.ControlledScreenBase;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.WindowType;
import jw.kingdom.hall.kingdomtimer.javafx.utils.PlatformUtils;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class HandyWindowPresenter extends ControlledScreenBase implements SpsControllerForTime.Input {

    @FXML
    private HBox mainContainer;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblNow;

    @FXML
    private HBox hbTimeControlsContainer;

    private double lastSize = -1;

    @Override
    protected void onPreCreate() {
        super.onPreCreate();
        setupZoom();
        lblNow.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindowData().getCountdown().addTimeDisplay(new TimeDisplayController(lblTime));
        hbTimeControlsContainer.getChildren().add(new SpsControllerForTime(this).getView());
        setupTaskNameUpdating();
        setupAutoChangingSize();
    }

    private void setupTaskNameUpdating() {
        getWindowData().getCountdown().addListener(new CountdownListener() {
            @Override
            public void onTaskStart(Task task) {
                super.onTaskStart(task);
                PlatformUtils.runOnUiThread(()->lblNow.setText(task.getName()));
            }

            @Override
            protected void onStop() {
                super.onStop();
                PlatformUtils.runOnUiThread(()->lblNow.setText(""));
            }
        });
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

    private HandyWindow getHandyWindow() {
        return ((HandyWindow) getWindowsContainer().getAppWindow(WindowType.WIDGET));
    }

    @Override
    protected void onCreate(URL location, ResourceBundle resources) {
        super.onCreate(location, resources);
    }

    //Option hide widget placed in time control has substituted this
    public void minifyAction() {
        window.getStage().setIconified(true);
    }

    @Override
    protected Region getMainContainer() {
        return mainContainer;
    }

    @Override
    public ScheduleController getSchedule() {
        return getWindowData().getSchedule();
    }

    @Override
    public CountdownController getCountdown() {
        return getWindowData().getCountdown();
    }
}
