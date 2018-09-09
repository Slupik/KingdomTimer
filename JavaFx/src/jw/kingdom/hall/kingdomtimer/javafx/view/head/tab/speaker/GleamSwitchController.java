package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.speaker;

import javafx.scene.control.CheckBox;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.entity.time.gleam.GleamSwitcher;

/**
 * All rights reserved & copyright ©
 */
class GleamSwitchController {

    private final Input input;

    GleamSwitchController(Input input){
        this.input = input;
        bindConfig();
        init();
    }

    private void init() {
        getBox().selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue!=newValue) {
                getSwitcher().setEnabled(newValue);
            }
        });
    }

    private void bindConfig() {
        getBox().setSelected(getConfig().isEnabledGleaming());
        getBox().selectedProperty().addListener((observable, oldValue, newValue) ->
                getConfig().setEnabledGleaming(newValue));
        getSwitcher().setEnabled(getConfig().isEnabledGleaming());
    }

    private GleamSwitcher getSwitcher() {
        return input.getGleamSwitcher();
    }

    private CheckBox getBox() {
        return input.getBoxForGleam();
    }

    private Config getConfig() {
        return input.getConfig();
    }

    interface Input {
        Config getConfig();
        CheckBox getBoxForGleam();
        GleamSwitcher getGleamSwitcher();
    }
}
