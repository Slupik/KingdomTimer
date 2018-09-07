package jw.kingdom.hall.kingdomtimer.javafx.view.head;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.screen.ControlledScreenBase;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.TabPresentable;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import static jw.kingdom.hall.kingdomtimer.javafx.view.head.TabView.*;

/**
 * All rights reserved & copyright ©
 */
public class HeadWindowPresenter extends ControlledScreenBase {

    @FXML
    public TabPane mainContainer;

    @FXML
    public Tab tabTimeControl;

    @FXML
    public HBox hbTimeControl;

    @FXML
    public Tab tabSpeakerScreen;

    @FXML
    public HBox hbSpeakerScreen;

    @FXML
    private Tab tabRecording;

    @FXML
    private HBox hbRecording;

    private final HashMap<TabView, TabPresentable> tabPresenters = new HashMap<>();

    @Override
    protected void onCreate(URL location, ResourceBundle resources) {
        super.onCreate(location, resources);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setScreens();
    }

    private void setScreens() {
        setViewToContainer(hbTimeControl, TIME);
        setViewToContainer(hbSpeakerScreen, SPEAKER);
        setViewToContainer(hbRecording, RECORDING);
    }

    private void setViewToContainer(HBox container, TabView view) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(view.PATH));
            Parent element = myLoader.load();
            TabPresentable controller = myLoader.getController();
            controller.setWindowsContainer(getWindowsContainer());
            controller.setWindowData(getWindowData());
            container.getChildren().setAll(element);
            tabPresenters.put(view, myLoader.getController());
        } catch (Exception e) {
            e.printStackTrace();
            Node screenNode = getErrorPane();
            container.getChildren().setAll(screenNode);
        }
    }

    private Node getErrorPane() {
        Pane errorPane = new Pane();
        errorPane.getChildren().add(new Label("Error while loading view"));
        return errorPane;
    }

    @Override
    protected Region getMainContainer() {
        return mainContainer;
    }
}
