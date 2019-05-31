package jw.kingdom.hall.kingdomtimer.webui.view.panel;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import jw.kingdom.hall.kingdomtimer.webui.domain.screen.ControlledScreenBase;

import java.net.URL;

public class PanelMainView extends ControlledScreenBase {
    private final static String PAGE_PATH = "layout/window/panel/page/index.html";

    @FXML
    private StackPane mainContainer;

    @FXML
    private WebView wvUI;

    @Override
    protected void onSetup() {
        super.onSetup();

        wvUI.getEngine().setJavaScriptEnabled(true);
        loadGUI();
    }

    private void loadGUI() {
        URL resource = getClass().getClassLoader().getResource(PAGE_PATH);
        if(resource!=null) {
            wvUI.getEngine().load(resource.toExternalForm());
        } else {
            wvUI.getEngine().loadContent("Required resource is unavailable at specific address ("+PAGE_PATH+").");
        }
    }

    @Override
    protected Region getMainContainer() {
        return mainContainer;
    }
}
