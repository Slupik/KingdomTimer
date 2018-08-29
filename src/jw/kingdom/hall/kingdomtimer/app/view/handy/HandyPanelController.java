package jw.kingdom.hall.kingdomtimer.app.view.handy;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import jw.kingdom.hall.kingdomtimer.app.view.common.ControlledScreenImpl;
import jw.kingdom.hall.kingdomtimer.app.view.loader.ScreenPaths;
import jw.kingdom.hall.kingdomtimer.app.view.loader.ScreenUtils;
import jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.TabScreens;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class HandyPanelController extends ControlledScreenImpl implements Initializable {

    @FXML
    HBox mainContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    protected Region getMainContainer() {
        return mainContainer;
    }
}
