package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class FastPanelController {
    static void executeIfSave(int seconds, Runnable callback) {
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
}
