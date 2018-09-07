package jw.kingdom.hall.kingdomtimer.javafx;

import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.javafx.entity.bussines.BackupController;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.WindowInput;
import jw.kingdom.hall.kingdomtimer.javafx.view.handy.HandyWindow;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.HeadWindow;
import jw.kingdom.hall.kingdomtimer.javafx.view.speaker.SpeakerWindow;
import jw.kingdom.hall.kingdomtimer.entity.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

/**
 * All rights reserved & copyright ©
 */
public class App {

    private final Input input;
    private BackupController backup;
    private Config config;

    public App(Input input) {
        this.input = input;
        this.backup = input.getBackup();
        this.config = input.getConfig();
    }

    public void start(Stage primaryStage) throws Exception {
        WindowInput wInput = getWindowInput();
        new HeadWindow(primaryStage, wInput).init();
        new SpeakerWindow(new Stage(), wInput).init();
        new HandyWindow(new Stage(), wInput).init();

        new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wInput.getSchedule().addTask(getTestsTask());
        }).start();
    }

    private ObservableTask getTestsTask() {
        TaskBean task = new TaskBean();
        task.setName("Example of a simple task");
        task.setSeconds(120);
        return task;
    }

    private WindowInput getWindowInput() {
        return new WindowInput() {
            @Override
            public ScheduleController getSchedule() {
                return input.getSchedule();
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
    }
}
