package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.speaker;

import javafx.scene.control.CheckBox;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.javafx.temp.MultimediaPreviewer;

/**
 * All rights reserved & copyright ©
 */
class MultimediaPreviewBoxController {

    private final Input input;

    MultimediaPreviewBoxController(Input input){
        this.input = input;
        bindConfig();
        init();
    }

    private void init() {
        getBox().selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue!=newValue) {
                MultimediaPreviewer.getInstance().showPreviews(newValue);
            }
        });
    }

    private void bindConfig() {
        getBox().setSelected(getConfig().isEnabledShowMultimedia());
        getBox().selectedProperty().addListener((observable, oldValue, newValue) ->
                getConfig().setEnabledShowMultimedia(newValue));
        MultimediaPreviewer.getInstance().showPreviews(getConfig().isEnabledShowMultimedia());
    }

    private CheckBox getBox() {
        return input.getBoxForPreviewVisibility();
    }

    private Config getConfig() {
        return input.getConfig();
    }

    interface Input {
        Config getConfig();
        CheckBox getBoxForPreviewVisibility();
    }
}
