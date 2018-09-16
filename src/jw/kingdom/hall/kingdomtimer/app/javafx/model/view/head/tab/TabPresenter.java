package jw.kingdom.hall.kingdomtimer.app.javafx.model.view.head.tab;

import javafx.fxml.Initializable;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.container.WindowsContainer;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public abstract class TabPresenter implements TabPresentable, Initializable {
    private WindowsContainer windowsContainer;
    private WindowInput data;

    @Override
    public void setWindowsContainer(WindowsContainer windowsContainer) {
        this.windowsContainer = windowsContainer;
        runPostCreateIfConditionsMet();
    }

    @Override
    public WindowsContainer getWindowsContainer() {
        return windowsContainer;
    }

    @Override
    public void setWindowData(WindowInput input) {
        this.data = input;
        runPostCreateIfConditionsMet();
    }

    @Override
    public WindowInput getWindowData() {
        return data;
    }

    private void runPostCreateIfConditionsMet() {
        if(data!=null && windowsContainer!=null) {
            onSetup();
        }
    }

    @Override
    public final void initialize(URL location, ResourceBundle resources) {
        onCreate(location, resources);
    }

    protected void onCreate(URL location, ResourceBundle resources) {

    }

    public void onSetup() {

    }
}
