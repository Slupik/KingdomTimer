package jw.kingdom.hall.kingdomtimer.javafx.domain.app;

import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.WindowType;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.container.WindowsContainer;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.container.WindowsContainerImpl;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.HeadWindow;
import jw.kingdom.hall.kingdomtimer.javafx.view.speaker.SpeakerWindow;
import jw.kingdom.hall.kingdomtimer.javafx.view.widget.HandyWindow;
import jw.kingdom.hall.kingdomtimer.domain.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.monitor.MonitorListManager;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MonitorPreviewController;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.RecordControl;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;
import jw.kingdom.hall.kingdomtimer.domain.task.provider.TasksProvider;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeController;

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
        buildWindow(HeadWindow.class, primaryStage, WindowType.PANEL);
        buildWindow(SpeakerWindow.class, new Stage(), WindowType.SPEAKER);
        buildWindow(HandyWindow.class, new Stage(), WindowType.WIDGET);
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
            public AppConfig getConfig() {
                return appInput.getConfig();
            }

            @Override
            public RecordControl getRecorder() {
                return appInput.getRecorder();
            }

            @Override
            public Schedule getSchedule() {
                return appInput.getSchedule();
            }

            @Override
            public Countdown getCountdown() {
                return appInput.getCountdown();
            }

            @Override
            public TimeController getTimeController() {
                return appInput.getTimeController();
            }

            @Override
            public MonitorPreviewController getSpeakerPreviewController() {
                return appInput.getSpeakerPreviewController();
            }

            @Override
            public TasksProvider getTasksProvider() {
                return appInput.getTasksProvider();
            }

            @Override
            public MonitorListManager getMonitorsManager() {
                return appInput.getMonitorsManager();
            }

            @Override
            public WindowsContainer getWindowsContainer() {
                return container;
            }
        };
    }

}
