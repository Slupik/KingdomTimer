package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.speaker;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import jw.kingdom.hall.kingdomtimer.config.model.Config;

import java.util.Optional;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class RefreshRateDialogs {

    private final Config config;

    RefreshRateDialogs(Config config){
        this.config = config;
    }

    void showTooLowValue() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd!!");
        alert.setHeaderText("Wpisałeś za małą wartość, minimalna ilość milisekund to " + String.valueOf(getConfig().getMinRefreshRate()));

        alert.showAndWait();
    }

    void showWarning(Runnable onYes, Runnable onNo) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Uwaga!");
        alert.setHeaderText("Wpisałeś wartość poniżej zalecanej (" + String.valueOf(getConfig().getWarningRefreshRate()) + ")");
        alert.setContentText("Czy na pewno chcesz jej użyć?");

        ButtonType btnYes = new ButtonType("Tak", ButtonBar.ButtonData.YES);
        ButtonType btnNo = new ButtonType("Nie", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(btnYes, btnNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnYes) {
            onYes.run();
        } else {
            onNo.run();
        }
    }

    private Config getConfig() {
        return config;
    }
}
