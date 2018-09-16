package jw.kingdom.hall.kingdomtimer.app.javafx.common.sps;

import com.google.common.io.Resources;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static jw.kingdom.hall.kingdomtimer.app.javafx.utils.ButtonUtils.loadMediumImage;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class StartPauseStopView extends HBox {
    private Controller controller;
    private List<Listener> listeners = new ArrayList<>();

    private boolean pause = false;
    private boolean stop = true;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnStop;

    public StartPauseStopView() {
        bindView();
        buildView();
    }

    private void buildView() {
        setupView();
        loadStopImage();
        setupView();
    }

    private void setupView() {
        if(stop) {
            btnStart.setVisible(true);
            loadStartImage();
            btnStop.setVisible(false);
        } else if(pause) {
            btnStart.setVisible(true);
            loadStartImage();
            btnStop.setVisible(true);
        } else {
            btnStart.setVisible(true);
            loadPauseImage();
            btnStop.setVisible(true);
        }
    }

    @FXML
    void onStartPauseAction(ActionEvent event) {
        if(stop) {
            start();
        } else if(pause) {
            unPause();
        } else {
            pause();
        }
    }

    @FXML
    void onStopAction(ActionEvent event) {
        stop();
    }

    private void loadStopImage() {
        loadMediumImage(btnStop, "icons/baseline_stop_black_48dp.png");
    }

    private void loadPauseImage() {
        loadMediumImage(btnStart, "icons/baseline_pause_black_48dp.png");
    }

    private void loadStartImage() {
        loadMediumImage(btnStart, "icons/baseline_play_arrow_black_48dp.png");
    }

    public void pause() {
        if(isActionToStop(ActionType.PAUSE)) {
            return;
        }
        setupForPause();

        for(Listener listener:listeners) {
            listener.onPause();
        }
    }

    public void setupForPause() {
        pause = true;
        stop = false;
        setupView();
    }

    public void unPause() {
        if(isActionToStop(ActionType.UNPAUSE)) {
            return;
        }
        setupForUnPause();

        for(Listener listener:listeners) {
            listener.onUnPause();
        }
    }

    public void setupForUnPause() {
        pause = false;
        stop = false;
        setupView();
    }

    public void stop() {
        if(isActionToStop(ActionType.STOP)) {
            return;
        }
        setupForStop();

        for(Listener listener:listeners) {
            listener.onStop();
        }
    }

    public void setupForStop() {
        pause = false;
        stop = true;
        setupView();
    }

    public void start() {
        if(isActionToStop(ActionType.START)) {
            return;
        }
        setupForStart();

        for(Listener listener:listeners) {
            listener.onStart();
        }
    }

    public void setupForStart() {
        pause = false;
        stop = false;
        setupView();
    }

    private boolean isActionToStop(ActionType type) {
        return (controller != null && !controller.isToExecuteSPSAction(type));
    }

    private void bindView() {
        URL url = Resources.getResource("layout/custom/sps/spsView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public interface Controller {
        boolean isToExecuteSPSAction(ActionType type);
    }

    public enum ActionType {
        START,
        STOP,
        PAUSE,
        UNPAUSE
    }

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public void removeListener(Listener listener){
        listeners.remove(listener);
    }

    public interface Listener {
        void onStart();
        void onPause();
        void onUnPause();
        void onStop();
    }
}
