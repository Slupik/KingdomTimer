package jw.kingdom.hall.kingdomtimer.javafx;

import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.entity.time.buzzer.BuzzerPlayer;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleProvider;
import jw.kingdom.hall.kingdomtimer.javafx.entity.bussines.BackupController;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.WindowType;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.container.WindowsContainer;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.container.WindowsContainerImpl;
import jw.kingdom.hall.kingdomtimer.javafx.view.handy.HandyWindow;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.HeadWindow;
import jw.kingdom.hall.kingdomtimer.javafx.view.speaker.SpeakerWindow;
import jw.kingdom.hall.kingdomtimer.entity.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * All rights reserved & copyright ©
 */
public class App {

    private final Input input;
    private final WindowsContainer container = new WindowsContainerImpl();

    public App(Input input) {
        this.input = input;
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
            public WindowsContainer getWindowsContainer() {
                return container;
            }

            @Override
            public ScheduleController getSchedule() {
                return input.getSchedule();
            }

            @Override
            public CountdownController getCountdown() {
                return input.getCountdown();
            }

            @Override
            public BuzzerPlayer getBuzzer() {
                return input.getBuzzer();
            }

            @Override
            public ScheduleProvider getScheduleProvider() {
                return input.getScheduleProvider();
            }

            @Override
            public BackupController getBackup() {
                return input.getBackup();
            }

            @Override
            public Config getConfig() {
                return input.getConfig();
            }

            @Override
            public Recorder getRecorder() {
                return input.getRecorder();
            }
        };
    }

    public interface Input {
        BackupController getBackup();
        Config getConfig();
        Recorder getRecorder();
        ScheduleController getSchedule();
        CountdownController getCountdown();
        BuzzerPlayer getBuzzer();
        ScheduleProvider getScheduleProvider();
    }
}
