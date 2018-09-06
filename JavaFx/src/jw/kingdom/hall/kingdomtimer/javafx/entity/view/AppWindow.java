package jw.kingdom.hall.kingdomtimer.javafx.entity.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.javafx.loader.DefaultViewManager;
import jw.kingdom.hall.kingdomtimer.javafx.loader.ViewManager;
import jw.kingdom.hall.kingdomtimer.javafx.window.WindowSettings;

/**
 * All rights reserved & copyright ©
 */
public abstract class AppWindow extends StackPane {
    protected final ViewManager viewManager;
    protected final Stage stage;
    protected final Scene scene;
    protected final Group root;

    public AppWindow(Stage stage) {
        this.stage = stage;
        root = new Group();
        scene = new Scene(root);

        viewManager = new DefaultViewManager();
        viewManager.setWindow(this);
    }

    public void init() {
        onPreInit();
        stage.setTitle(WindowSettings.TITLE);

        loadScreens();
        root.getChildren().add(this);
        setMainView();

        stage.setScene(scene);
        onPreShow();
        stage.show();
        onPostInit();
    }

    protected abstract void loadScreens();
    protected abstract void setMainView();

    protected abstract void onPreInit();
    protected abstract void onPreShow();
    protected abstract void onPostInit();

    public Stage getStage() {
        return stage;
    }
}
