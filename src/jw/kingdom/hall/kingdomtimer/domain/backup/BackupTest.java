package jw.kingdom.hall.kingdomtimer.domain.backup;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.domain.backup.entity.OfflineMeetingBean;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BackupTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MeetingTask task = new MeetingTask();
        task.setType(MeetingTask.Type.LIVING);
        task.setName("Cool NAME");

        OfflineMeetingBean bean = new OfflineMeetingBean(task);
        String parsed = new Gson().toJson(bean);
        System.out.println("parsed = " + parsed);
        OfflineMeetingBean copy = new Gson().fromJson(parsed, OfflineMeetingBean.class);
        System.out.println("copy.getType() = " + copy.getType());
        System.out.println("copy.getName() = " + copy.getName());

        System.exit(0);
    }
}
