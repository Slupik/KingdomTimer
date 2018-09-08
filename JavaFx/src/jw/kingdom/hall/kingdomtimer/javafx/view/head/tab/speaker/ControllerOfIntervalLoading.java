package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.speaker;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.javafx.custom.AdvancedTextField;
import jw.kingdom.hall.kingdomtimer.javafx.temp.MultimediaPreviewer;

/**
 * All rights reserved & copyright ©
 */
class ControllerOfIntervalLoading {

    private final Input input;
    private int lastSavedInterval = -1;

    ControllerOfIntervalLoading(Input input){
        this.input = input;
    }

    void load(){
        int current = Integer.parseInt(getField().getSaveText());
        if(current<getConfig().getMinRefreshRate()) {
            getDialogShower().showTooLowValue();
            repairInterval();
        } else if(current<getConfig().getWarningRefreshRate()) {
            getDialogShower().showWarning(
                    this::saveInterval,
                    this::repairInterval
            );
        } else {
            saveInterval();
        }
    }

    void loadDefault(){
        getField().setText(String.valueOf(getConfig().getDefaultRefreshRate()));
        saveInterval();
    }

    private RefreshRateDialogs getDialogShower() {
        return new RefreshRateDialogs(getConfig());
    }

    private void repairInterval() {
        getField().setText(String.valueOf(lastSavedInterval));
    }

    private void saveInterval() {
        int current = Integer.parseInt(getField().getSaveText());
        lastSavedInterval = current;
        MultimediaPreviewer.getInstance().setRefreshInterval(current);
        getConfig().setActualRefreshRate(current);
    }

    private AdvancedTextField getField() {
        return input.getField();
    }

    private Config getConfig() {
        return input.getConfig();
    }

    interface Input {
        Config getConfig();
        AdvancedTextField getField();
    }
}
