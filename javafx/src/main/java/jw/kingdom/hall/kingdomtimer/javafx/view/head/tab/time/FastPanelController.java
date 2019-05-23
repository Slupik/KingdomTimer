package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time.timedirect.BtnTimeDirectForPanel;
import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeController;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;

import java.util.Optional;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class FastPanelController {

    private final Input input;

    FastPanelController(Input input) {
        this.input = input;
    }

    void handleLoadTimeAction() {
        executeIfSave(getFiled().getAllSeconds(), ()->{
            TaskBean task = new TaskBean();
            task.setTime(getFiled().getAllSeconds());
            getFiled().setSeconds(0);
            task.setCountdownDown(getDirectController().isDirectDown());
            getDirectController().reset();
            getTimer().start(task);
        });
    }

    void handleAddTime() {
        executeIfSave(getFiled().getAllSeconds(), ()->{
            getTimer().addTime(getFiled().getAllSeconds());
        });
    }

    void handleRemoveTime() {
        executeIfSave(getFiled().getAllSeconds(), ()->{
            getTimer().addTime(Math.negateExact(getFiled().getAllSeconds()));
        });
    }

    private TimeController getTimer() {
        return input.getTimer();
    }

    private TimeField getFiled() {
        return input.getFastTimeField();
    }

    private BtnTimeDirectForPanel getDirectController() {
        return input.getFastDirectController();
    }

    private void executeIfSave(int seconds, Runnable callback) {
        if(seconds<3600) {
            callback.run();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Wymagane potwierdzenie");
            alert.setHeaderText("Czy na pewno chcesz użyć tak dużej ilości czasu?");
            alert.setContentText("Wykryto, że chcesz zmienić obecny czas o "+ seconds/3600 + " godzin "+seconds%3600/60+" minut i "+seconds%3600+" sekund.");

            ButtonType btnYes = new ButtonType("Tak", ButtonBar.ButtonData.YES);
            ButtonType btnNo = new ButtonType("Nie", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(btnYes, btnNo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == btnYes) {
                callback.run();
            }
        }
    }

    interface Input {
        TimeController getTimer();
        TimeField getFastTimeField();
        BtnTimeDirectForPanel getFastDirectController();
    }
}
