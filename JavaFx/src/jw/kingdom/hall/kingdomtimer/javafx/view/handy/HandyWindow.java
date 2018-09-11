package jw.kingdom.hall.kingdomtimer.javafx.view.handy;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.WindowInput;

import static jw.kingdom.hall.kingdomtimer.javafx.view.handy.HandyWindow.Screens.MAIN;

/**
 * All rights reserved & copyright ©
 */
public class HandyWindow extends AppWindow {

    public HandyWindow(Stage stage, WindowInput input) {
        super(stage, input);
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
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
    }

    @Override
    protected void onPreShow() {
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        stage.setWidth(300);
        stage.setHeight(110);
    }

    @Override
    protected void onPostShow() {
        new MobilityController(stage, root);

        new Thread(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(()-> getStage().sizeToScene());
        }).start();
    }

    public enum Screens {
        MAIN("main", "/layout/window/handy/main.fxml"),
        ;

        public final String name;
        public final String path;

        Screens(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }
}
