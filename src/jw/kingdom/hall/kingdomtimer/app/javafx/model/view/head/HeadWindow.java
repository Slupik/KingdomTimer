package jw.kingdom.hall.kingdomtimer.app.javafx.model.view.head;

import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.app.CrashMaker;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.domain.backup.BackupManager;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.VoiceRecorder;

import static jw.kingdom.hall.kingdomtimer.app.javafx.model.view.head.HeadWindow.Screens.MAIN;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class HeadWindow extends AppWindow {

    public HeadWindow(Stage stage, WindowInput input) {
        super(stage, input);
    }

    @Override
    protected boolean isToShowAtInit() {
        return true;
    }

    public void loadScreens() {
        viewManager.loadScreen(MAIN.name, MAIN.path);
    }

    @Override
    protected void setMainView() {
        viewManager.setScreen(MAIN.name);
    }

    @Override
    protected void onPostLoadViews() {
        super.onPostLoadViews();
        stage.setMaximized(true);
    }

    @Override
    protected void onPostShow() {
        super.onPostShow();

        stage.setOnCloseRequest(event -> {
            if(VoiceRecorder.getInstance().isRecording()) {
                event.consume();
                StillRecordingAlert.show();
            } else {
                BackupManager.delete();
                System.exit(0);
            }
        });

//        crashOnDemand();
    }

    /**
     * Useful for testing backup system
     */
    private void crashOnDemand() {
        new Thread(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CrashMaker.crashApp();
        }).start();
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    public enum Screens {
        MAIN("main", "/layout/window/head/main.fxml"),
        ;

        public final String name;
        public final String path;

        Screens(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }
}
