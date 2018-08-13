package jw.kingdom.hall.kingdomtimer.view.panel;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.view.utils.Screens;
import jw.kingdom.hall.kingdomtimer.view.utils.WindowController;
import jw.kingdom.hall.kingdomtimer.view.utils.WindowSettings;

/**
 * All rights reserved & copyright ©
 */
public class PanelWindow {
    public final static WindowController CONTROLLER = new WindowController();

    private static Stage stage;
    private static Scene scene;
    private static Group root;

    public void build(Stage primaryStage){
        stage = primaryStage;
        root = new Group();

        stage.setTitle(WindowSettings.TITLE);

        loadScreens();
        root.getChildren().addAll(CONTROLLER.getScreensPane());
        CONTROLLER.setScreen(Screens.PANEL_CONTROLLING);

        scene = new Scene(root);
        stage.setScene(scene);
//        stage.setMaximized(true);
        stage.show();

        stage.setOnCloseRequest(event -> System.exit(0));
    }

    public void loadScreens() {
        CONTROLLER.loadScreen(Screens.PANEL_CONTROLLING);
    }

    public Stage getStage() {
        return stage;
    }
}
