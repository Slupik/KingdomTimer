package jw.kingdom.hall.kingdomtimer.webui.view.panel;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import jw.kingdom.hall.kingdomtimer.webui.domain.screen.ControlledScreenBase;

public class PanelMainView extends ControlledScreenBase {

    @FXML
    private StackPane mainContainer;

    @FXML
    private WebView wvUI;

    @Override
    protected void onSetup() {
        super.onSetup();
    }

    @Override
    protected Region getMainContainer() {
        return mainContainer;
    }
}
