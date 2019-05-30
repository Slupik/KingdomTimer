package jw.kingdom.hall.kingdomtimer.webui.domain.loader;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.util.Duration;
import jw.kingdom.hall.kingdomtimer.webui.domain.app.AppWindow;
import jw.kingdom.hall.kingdomtimer.webui.domain.di.WindowInput;
import jw.kingdom.hall.kingdomtimer.webui.domain.screen.ControlledScreen;

import java.io.IOException;
import java.util.HashMap;

public class DefaultViewManager implements ViewManager {
    private final WindowInput input;
    private AppWindow window;
    private HashMap<String, Node> screens = new HashMap<>();

    public DefaultViewManager(WindowInput input) {
        this.input = input;
    }

    @Override
    public void setWindow(AppWindow window) {
        this.window = window;
    }

    @Override
    public void addScreen(String name, Node screen){
        screens.put(name, screen);
    }

    @Override
    public Node getScreen(String name){
        return screens.get(name);
    }

    @Override
    public boolean loadScreen(String name, String path){
        try {
            FXMLLoader myLoader= new FXMLLoader(getClass().getResource(path));
            Parent loadScreen = myLoader.load();
            ControlledScreen myScreenController = myLoader.getController();
            myScreenController.setWindow(window);
            myScreenController.setWindowData(input);
            myScreenController.setViewManager(this);

            addScreen(name, loadScreen);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean unloadScreen(String name) {
        if(screens.remove(name)==null) {
            System.out.println("Screens " +name+ " didn't exist");
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean setScreen(final String name){
        if(screens.get(name) != null) { //screen loaded
            final DoubleProperty opacity = window.opacityProperty();

            if(!window.getChildren().isEmpty()) { //if there is more than one screen
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(1000), t -> {
                            window.getChildren().remove(0); //remove the displayed screen
                            window.getChildren().add(0, screens.get(name)); //add the screen
                            Timeline fadeIn = new Timeline(
                                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                    new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
                            fadeIn.play();
                        }, new KeyValue(opacity, 0.0)));
                fade.play();
                fade.setOnFinished(paramT -> doAutoSize());
            }else{
                window.setOpacity(0.0);
                window.getChildren().add(screens.get(name)); //no one else been displayed, then just show
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

    private void doAutoSize(){
        window.getStage().sizeToScene();
    }
}