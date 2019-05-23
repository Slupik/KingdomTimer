package jw.kingdom.hall.kingdomtimer.javafx.view.widget.screen;

import javafx.scene.control.Label;
import jw.kingdom.hall.kingdomtimer.javafx.utils.PlatformUtils;
import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeController;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeListenerProxy;

/**
 * All rights reserved & copyright ©
 */
class TaskNamePresenter {

    private final Input input;

    TaskNamePresenter(Input input) {
        this.input = input;
        init();
    }

    private void init() {
        getLabelWithName().setText("");
        getLabelWithNameOfNext().setText("");
        getTimer().addListener(new TimeListenerProxy() {
            @Override
            public void onStart(TaskBean task) {
                super.onStart(task);
                PlatformUtils.runOnUiThread(()-> getLabelWithName().setText(task.getName()));
                if(getTimer().getList().size()>0) {
                    PlatformUtils.runOnUiThread(()-> getLabelWithNameOfNext().setText(
                            getTimer().getList().get(0).getName()));
                } else {
                    PlatformUtils.runOnUiThread(()-> getLabelWithNameOfNext().setText(""));
                }
            }

            @Override
            public void onStop() {
                super.onStop();
                PlatformUtils.runOnUiThread(()-> getLabelWithName().setText(""));
            }
        });
    }

    private Label getLabelWithName() {
        return input.getLabelWithName();
    }

    private Label getLabelWithNameOfNext() {
        return input.getLabelWithNameOfNext();
    }

    private TimeController getTimer() {
        return input.getTimer();
    }

    interface Input {
        Label getLabelWithName();
        Label getLabelWithNameOfNext();
        TimeController getTimer();
    }
}
