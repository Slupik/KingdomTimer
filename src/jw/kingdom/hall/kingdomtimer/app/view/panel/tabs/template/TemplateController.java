package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.template;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.app.view.common.ControlledScreenImpl;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * EXAMPLE
 */
@SuppressWarnings("unused")
public class TemplateController extends ControlledScreenImpl implements Initializable {

    @FXML
    VBox vbMainContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    protected Region getMainContainer() {
        return vbMainContainer;
    }
}
