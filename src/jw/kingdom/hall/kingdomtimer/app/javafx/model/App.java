package jw.kingdom.hall.kingdomtimer.app.javafx.model;

import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.view.widget.HandyWindow;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.WindowType;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.container.WindowsContainer;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.container.WindowsContainerImpl;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * All rights reserved & copyright ©
 */
public class App {

    private final AppInput appInput;
    private final WindowsContainer container = new WindowsContainerImpl();

    public App(AppInput appInput) {
        this.appInput = appInput;
    }

    public void start(Stage primaryStage) throws Exception {
        initWindows(primaryStage);
    }

    private void initWindows(Stage primaryStage) {
//        buildWindow(HeadWindow.class, primaryStage, WindowType.PANEL);
//        buildWindow(SpeakerWindow.class, new Stage(), WindowType.SPEAKER);
        buildWindow(HandyWindow.class, primaryStage, WindowType.WIDGET);
    }

    private void buildWindow(Class<? extends AppWindow> appWindowClass, Stage stage, WindowType type) {
        try {
            Constructor<? extends AppWindow> ctor = appWindowClass.getConstructor(Stage.class, WindowInput.class);
            AppWindow window = ctor.newInstance(stage, getWindowInput());
            window.init();
            container.putAppWindow(type, window);
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private WindowInput getWindowInput() {
        return new WindowInput() {
            @Override
            public Config getConfig() {
                return appInput.getConfig();
            }

            @Override
            public Recorder getRecorder() {
                return appInput.getRecorder();
            }

            @Override
            public WindowsContainer getWindowsContainer() {
                return container;
            }
        };
    }

}
