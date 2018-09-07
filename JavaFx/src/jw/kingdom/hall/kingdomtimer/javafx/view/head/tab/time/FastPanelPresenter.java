package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.javafx.control.time.direct.BtnTimeDirectForPanel;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;

import java.util.Optional;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class FastPanelPresenter {

    private final Data data;

    FastPanelPresenter(Data data) {
        this.data = data;
    }

    void handleRemoveTime() {
        int time = getTime();
        executeIfUserAllow(time, ()-> getCountdown().subtractTime(time));
    }

    void handleAddTime() {
        int time = getTime();
        executeIfUserAllow(time, ()-> getCountdown().addTime(time));
    }

    void handleLoadTime() {
        int time = getTime();
        executeIfUserAllow(time, ()-> {
            Task task = new TaskBean();
            task.setName("???");
            task.setSeconds(time);
            task.setDirectDown(data.getFastDirectController().isDirectDown());
            getCountdown().start(task);

            data.getFastTimeField().setSeconds(0);
            data.getFastDirectController().reset();
        });
    }

    private static void executeIfUserAllow(int seconds, Runnable callback) {
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

    private int getTime(){
        return data.getFastTimeField().getAllSeconds();
    }

    private CountdownController getCountdown(){
        return data.getCountdown();
    }

    interface Data {
        TimeField getFastTimeField();
        CountdownController getCountdown();
        BtnTimeDirectForPanel getFastDirectController();
    }
}
