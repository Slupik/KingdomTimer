package jw.kingdom.hall.kingdomtimer.webui.view.panel;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import jw.kingdom.hall.kingdomtimer.webui.domain.page.PageAsMonolithLoader;
import jw.kingdom.hall.kingdomtimer.webui.domain.page.PageLoadException;
import jw.kingdom.hall.kingdomtimer.webui.domain.page.PageLoader;
import jw.kingdom.hall.kingdomtimer.webui.domain.screen.ControlledScreenBase;

public class PanelMainView extends ControlledScreenBase {

    @FXML
    private StackPane mainContainer;

    @FXML
    private WebView wvUI;

    @Override
    protected void onSetup() {
        super.onSetup();

        wvUI.getEngine().setJavaScriptEnabled(true);
        loadGUI(new PageAsMonolithLoader());
    }

    private void loadGUI(PageLoader loader) {
        try {
            String parsedPage = loader.getParsedPage("layout/window/panel/page/");
            wvUI.getEngine().loadContent(parsedPage);
        } catch (PageLoadException e) {
            e.printStackTrace();
            wvUI.getEngine().loadContent(e.getAsText());
        }
    }

    @Override
    protected Region getMainContainer() {
        return mainContainer;
    }
}
