package jw.kingdom.hall.kingdomtimer.app.view.panel;

import javafx.scene.control.Alert;

/**
 * All rights reserved & copyright ©
 */
class StillRecordingAlert {
    static void show() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Uwaga!!");
        alert.setHeaderText("Wciąż trwa nagrywanie lub konwersja. Nie wyłączaj jeszcze programu.");

        alert.showAndWait();
    }
}
