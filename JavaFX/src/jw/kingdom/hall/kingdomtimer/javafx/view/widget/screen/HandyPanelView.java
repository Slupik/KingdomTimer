package jw.kingdom.hall.kingdomtimer.javafx.view.widget.screen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import jw.kingdom.hall.kingdomtimer.javafx.common.controller.time.display.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.javafx.common.zoom.ZoomController;
import jw.kingdom.hall.kingdomtimer.javafx.domain.screen.ControlledScreenBase;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.WindowType;
import jw.kingdom.hall.kingdomtimer.javafx.view.widget.HandyWindow;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class HandyPanelView extends ControlledScreenBase implements TimeControlsPresenter.Input, TaskNamePresenter.Input,
        SizeAdjustmentController.Input {

    @FXML
    private HBox mainContainer;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblNow;

    @FXML
    private Label lblNext;

    @FXML
    private HBox hbTimeControlsContainer;

    @Override
    protected void onSetup() {
        super.onSetup();
        new TimeControlsPresenter(this);
        new TaskNamePresenter(this);

        new ZoomController(getMainContainer());
        new SizeAdjustmentController(this);

        getTimer().addDisplay(new TimeDisplayController(lblTime));
    }

    @FXML
    void minifyAction(ActionEvent event) {
        getHandyWindow().getStage().setIconified(true);
    }

    @Override
    protected Region getMainContainer() {
        return mainContainer;
    }

    private HandyWindow getHandyWindow() {
        return (HandyWindow) getWindowData().getWindowsContainer().getAppWindow(WindowType.WIDGET);
    }

    @Override
    public Pane getSpsContainer() {
        return hbTimeControlsContainer;
    }

    @Override
    public Label getLabelWithName() {
        return lblNow;
    }

    @Override
    public Label getLabelWithNameOfNext() {
        return lblNext;
    }

    @Override
    public AppWindow getParentWindow() {
        return getHandyWindow();
    }

    @Override
    public Label getLblWithTime() {
        return lblTime;
    }
}
