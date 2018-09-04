package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import jw.kingdom.hall.kingdomtimer.domain.backup.BackupManager;

import java.util.Optional;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class BackupPresenter {

    void run(){
        if(BackupManager.isBackupAvailable()) {
            showAskDialog();
        }
    }

    private void showAskDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Wykryto backup!");
        alert.setHeaderText("Program wykrył kopię zapasową ostatnich działań, czy chcesz je wczytać do programu?");
        alert.setContentText("Program automatycznie sporządza kopię zapasową wykonywanych działań. Jeżeli zostanie " +
                "zamknięty w nieprawidłowy sposób możliwe jest przywrócenie poprzedniego stanu.");

        ButtonType btnYes = new ButtonType("Tak", ButtonBar.ButtonData.YES);
        ButtonType btnNo = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnYes, btnNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnYes){
            BackupManager.restore();
        } else {
            BackupManager.delete();
        }
    }
}
