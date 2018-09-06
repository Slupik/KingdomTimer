package jw.kingdom.hall.kingdomtimer.javafx.window.handy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.ControlledScreenBase;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class HandyWindowPresenter extends ControlledScreenBase {

    @FXML
    private HBox mainContainer;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblNow;

    @FXML
    private HBox hbTimeControlsContainer;

    @Override
    protected void onPreCreate() {
        super.onPreCreate();
        setupZoom();
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

    @Override
    protected void onCreate(URL location, ResourceBundle resources) {
        super.onCreate(location, resources);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lblNow.setText("");
    }

    public void minifyAction(ActionEvent actionEvent) {
        window.getStage().setIconified(true);
    }

    @Override
    protected Region getMainContainer() {
        return mainContainer;
    }
}
