package jw.kingdom.hall.kingdomtimer.javafx.entity.view;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.layout.Region;
import jw.kingdom.hall.kingdomtimer.javafx.loader.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public abstract class ControlledScreenBase implements ControlledScreen, Initializable {
    protected AppWindow window;
    protected ViewManager viewManager;

    @Override
    public void setWindow(AppWindow window) {
        this.window = window;
        setupAutoMaxSize();
        runSetup();
    }

    @Override
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
        runSetup();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onPreCreate();
        onCreate(location, resources);
        runSetup();
    }

    private void runSetup() {
        if(window!=null && viewManager!=null) {
            Platform.runLater(this::setup);
        }
    }

    private void setup() {
        onStart();
    }

    protected void onPreCreate() {

    }

    protected void onCreate(URL location, ResourceBundle resources) {

    }

    protected void onStart() {

    }

    private void setupAutoMaxSize() {
        if(getMainContainer()==null) {
            return;
        }
        getMainContainer().minHeightProperty().bind(
                window.getStage().heightProperty()
        );
        getMainContainer().maxHeightProperty().bind(getMainContainer().minHeightProperty());
        getMainContainer().prefHeightProperty().bind(getMainContainer().minHeightProperty());

        getMainContainer().minWidthProperty().bind(
                window.getStage().widthProperty()
        );
        getMainContainer().maxWidthProperty().bind(getMainContainer().minWidthProperty());
        getMainContainer().prefWidthProperty().bind(getMainContainer().minWidthProperty());
    }

    protected abstract Region getMainContainer();
}
