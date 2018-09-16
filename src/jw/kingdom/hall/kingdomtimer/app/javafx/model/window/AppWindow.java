package jw.kingdom.hall.kingdomtimer.app.javafx.model.window;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.app.javafx.loader.DefaultViewManager;
import jw.kingdom.hall.kingdomtimer.app.javafx.loader.ViewManager;
import jw.kingdom.hall.kingdomtimer.app.view.loader.WindowSettings;

/**
 * All rights reserved & copyright ©
 */
public abstract class AppWindow extends StackPane {
    protected final ViewManager viewManager;
    protected final Stage stage;
    protected final WindowInput input;
    protected final Scene scene;
    protected final Group root;

    public AppWindow(Stage stage, WindowInput input) {
        this.stage = stage;
        this.input = input;
        root = new Group();
        scene = new Scene(root);

        viewManager = new DefaultViewManager(input);
        viewManager.setWindow(this);
    }

    public void init() {
        onPreInit();
        stage.setTitle(WindowSettings.TITLE);

        loadScreens();
        root.getChildren().add(this);
        setMainView();

        stage.setScene(scene);
        onPostLoadViews();
        if(isToShowAtInit()) {
            stage.show();
        }
        onPostShow();
        Platform.runLater(()->onPostInit());
    }

    protected abstract boolean isToShowAtInit();

    protected abstract void loadScreens();
    protected abstract void setMainView();

    protected void onPreInit() {

    }

    protected void onPostLoadViews() {

    }

    protected void onPostShow() {

    }

    protected void onPostInit() {

    }

    public Stage getStage() {
        return stage;
    }
}
