package jw.kingdom.hall.kingdomtimer.webui.domain.screen;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.layout.Region;
import jw.kingdom.hall.kingdomtimer.webui.domain.app.AppWindow;
import jw.kingdom.hall.kingdomtimer.webui.domain.di.WindowInput;
import jw.kingdom.hall.kingdomtimer.webui.domain.loader.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class ControlledScreenBase implements ControlledScreen, Initializable {
    protected AppWindow window;
    protected ViewManager viewManager;
    protected WindowInput data;

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
    public void setWindowData(WindowInput input) {
        this.data = input;
        runSetup();
    }

    @Override
    public final void initialize(URL location, ResourceBundle resources) {
        onPreCreate();
        onCreate(location, resources);
        runSetup();
    }

    private void runSetup() {
        if(window!=null && viewManager!=null && data!=null) {
            Platform.runLater(this::setup);
        }
    }

    private void setup() {
        onSetup();
    }

    protected void onPreCreate() {

    }

    protected void onCreate(URL location, ResourceBundle resources) {

    }

    protected void onSetup() {

    }

    private void setupAutoMaxSize() {
        if(getMainContainer()==null) {
            return;
        }
        SizeBinder.bindSize(window.getStage(), getMainContainer());
    }

    protected abstract Region getMainContainer();
}
