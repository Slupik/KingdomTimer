package jw.kingdom.hall.kingdomtimer.view.utils;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import jw.kingdom.hall.kingdomtimer.view.panel.PanelWindow;

import java.io.IOException;
import java.util.HashMap;

public class ScreensController extends StackPane {
//    private static final PanelWindow ControllingWindow = new PanelWindow();
    private final StageWindow stageWindow;

    private HashMap<String, Node> screens = new HashMap<>();

    ScreensController(StageWindow stageWindow) {
        super();
        this.stageWindow = stageWindow;
    }

    void addScreen(String name, Node screen){
        screens.put(name, screen);
    }

    Node getScreen(String name){
        return screens.get(name);
    }

    boolean loadScreen(String name, String path){
        try {
            FXMLLoader myLoader= new FXMLLoader(getClass().getResource(path));
            Parent loadScreen = myLoader.load();
            ControlledScreen myScreenController = myLoader.getController();
            myScreenController.setScreenParent(this);

            addScreen(name, loadScreen);//adding do HashMap
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    boolean unloadScreen(String name) {
        if(screens.remove(name)==null) {
            System.out.println("Screens " +name+ " didn't exist");
            return false;
        }else{
            return true;
        }
    }

    boolean setScreen(final String name){
        if(screens.get(name) != null) { //screen loaded
            final DoubleProperty opacity = opacityProperty();

            if(!getChildren().isEmpty()) { //if there is more than one screen
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>(){

                            @Override
                            public void handle(ActionEvent t){
                                getChildren().remove(0); //remove the displayed screen
                                getChildren().add(0, screens.get(name)); //add the screen
                                Timeline fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
                                fadeIn.play();
                            }
                        }, new KeyValue(opacity, 0.0)));
                fade.play();
                fade.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent paramT) {
                        doAutoSize();
                    }
                });
            }else{
                setOpacity(0.0);
                getChildren().add(screens.get(name)); //no one else been displayed, then just show
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(1000), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        }else{
            System.out.println("screen hasn't been loaded!!!\n");
            return false;
        }
    }

    void doAutoSize(){
//        stageWindow.getStage().sizeToScene();
    }

    void addAnimation(Timeline animation){
        animation.play();
        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }

    public Stage getStage(){
        return stageWindow.getStage();
    }
}
