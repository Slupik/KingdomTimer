package jw.kingdom.hall.kingdomtimer.javafx.view.head;

import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.javafx.entity.bussines.BackupController;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.AppWindow;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.WindowInput;

import static jw.kingdom.hall.kingdomtimer.javafx.view.head.HeadWindow.Screens.*;

/**
 * All rights reserved & copyright ©
 */
public class HeadWindow extends AppWindow {

    private final BackupController backup;

    public HeadWindow(Stage stage, WindowInput input) {
        super(stage, input);
        this.backup = input.getBackup();
    }

    @Override
    protected void loadScreens() {
        viewManager.loadScreen(MAIN.name, MAIN.path);
    }

    @Override
    protected void setMainView() {
        viewManager.setScreen(MAIN.name);
    }

    @Override
    protected void onPreInit() {

    }

    @Override
    protected void onPreShow() {

    }

    @Override
    protected void onPostShow() {
        stage.setOnCloseRequest(event -> {
            backup.delete();
            System.exit(0);
        });
    }

    public enum Screens {
        MAIN("main", "/layout/window/head/main.fxml"),
        ;

        public final String name;
        public final String path;

        Screens(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }
}
