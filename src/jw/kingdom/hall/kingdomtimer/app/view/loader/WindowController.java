package jw.kingdom.hall.kingdomtimer.app.view.loader;

import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class WindowController {
    private final ScreensController CONTROLLER;

    public StackPane getScreensPane(){
        return CONTROLLER;
    }

    public WindowController(StageWindow stageWindow){
        CONTROLLER = new ScreensController(stageWindow);
    }

    public void addScreen(String name, Node screen){
        CONTROLLER.addScreen(name, screen);
    }

    public Node getScreen(String name){
        return CONTROLLER.getScreen(name);
    }

    public boolean loadScreen(Screens screen){
        return CONTROLLER.loadScreen(screen.NAME, screen.PATH);
    }

    public boolean loadScreen(String name, String path){
        return CONTROLLER.loadScreen(name, path);
    }

    public boolean unloadScreen(String name) {
        return CONTROLLER.unloadScreen(name);
    }

    public boolean setScreen(Screens screen){
        return CONTROLLER.setScreen(screen.NAME);
    }

    public boolean setScreen(String name){
        return CONTROLLER.setScreen(name);
    }

    public void doAutoSize(){
        CONTROLLER.doAutoSize();
    }

    public void addAnimation(Timeline animation){
        CONTROLLER.addAnimation(animation);
    }
}
