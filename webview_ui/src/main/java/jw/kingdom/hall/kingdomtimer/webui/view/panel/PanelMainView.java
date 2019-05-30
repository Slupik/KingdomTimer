package jw.kingdom.hall.kingdomtimer.webui.view.panel;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import jw.kingdom.hall.kingdomtimer.webui.domain.screen.ControlledScreenBase;
import jw.kingdom.hall.kingdomtimer.webui.domain.utils.Resource;

import java.io.IOException;
import java.util.List;

public class PanelMainView extends ControlledScreenBase {

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

    //TODO refactor
    private void loadGUI() {
        try {
            String rawHTML = Resource.getContent("layout/window/panel/view/mainview.html");
            rawHTML = injectJavaScript(rawHTML);
            rawHTML = injectCSS(rawHTML);
            wvUI.getEngine().loadContent(rawHTML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String injectCSS(String rawHTML) {
        String placeholder = "<place_for_css></place_for_css>";
        return rawHTML.replace(placeholder, getCSSData());
    }

    private String getCSSData() {
        StringBuilder completeData = new StringBuilder();

        try {
            String folderPath = "layout/window/panel/styles/";
            List<String> fileNames = Resource.getResourceFiles(folderPath);

            for(String fileName: fileNames) {
                String completePath = folderPath+fileName;
                String content = Resource.getContent(completePath);

                completeData.append("<style>").append(content).append("</style>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return completeData.toString();
    }

    private String injectJavaScript(String rawHTML) {
        String placeholder = "<place_for_javascript></place_for_javascript>";
        return rawHTML.replace(placeholder, getJavaScriptData());
    }

    private String getJavaScriptData() {
        StringBuilder completeData = new StringBuilder();

        try {
            String folderPath = "layout/window/panel/scripts/";
            List<String> fileNames = Resource.getResourceFiles(folderPath);

            for(String fileName: fileNames) {
                String completePath = folderPath+fileName;
                String content = Resource.getContent(completePath);

                completeData.append("<script>").append(content).append("</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return completeData.toString();
    }

    @Override
    protected Region getMainContainer() {
        return mainContainer;
    }
}
