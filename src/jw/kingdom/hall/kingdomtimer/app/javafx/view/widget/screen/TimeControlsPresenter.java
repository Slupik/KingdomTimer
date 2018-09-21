package jw.kingdom.hall.kingdomtimer.app.javafx.view.widget.screen;

import javafx.scene.layout.Pane;
import jw.kingdom.hall.kingdomtimer.app.javafx.common.sps.StartPauseStopView;
import jw.kingdom.hall.kingdomtimer.app.javafx.utils.PlatformUtils;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeController;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeListenerProxy;

/**
 * All rights reserved & copyright ©
 */
class TimeControlsPresenter {

    private final Input input;
    private StartPauseStopView spsView;

    TimeControlsPresenter (Input input) {
        this.input = input;
        init();
    }

    private void init() {
        addSpsToContainer();
        initSpsController();
        bindTimerToSps();
        bindSpsToTimer();
    }

    private void bindSpsToTimer() {
        getTimer().addListener(new TimeListenerProxy() {
            @Override
            public void onStart(MeetingTask task) {
                super.onStart(task);
                PlatformUtils.runOnUiThread(()->{
                    spsView.setupForStart();
                });
            }

            @Override
            public void onPause() {
                super.onPause();
                PlatformUtils.runOnUiThread(()->{
                    spsView.setupForPause();
                });
            }

            @Override
            public void onResume() {
                super.onPause();
                PlatformUtils.runOnUiThread(()->{
                    spsView.setupForUnPause();
                });
            }

            @Override
            public void onStop() {
                super.onStop();
                PlatformUtils.runOnUiThread(()->{
                    spsView.setupForStop();
                });
            }
        });
    }

    private void bindTimerToSps() {
        spsView.addListener(new StartPauseStopView.Listener() {
            @Override
            public void onStart() {
                getTimer().startNext();
            }

            @Override
            public void onPause() {
                getTimer().pause();
            }

            @Override
            public void onResume() {
                getTimer().resume();
            }

            @Override
            public void onStop() {
                getTimer().stop();
            }
        });
    }

    private void initSpsController() {
        spsView.setController(type -> {
            if(type==StartPauseStopView.ActionType.START) {
                return getTimer().getList().size()!=0;
            }
            return true;
        });
    }

    private void addSpsToContainer() {
        spsView = new StartPauseStopView();
        getContainer().getChildren().add(spsView);
    }

    private TimeController getTimer() {
        return input.getTimer();
    }

    private Pane getContainer() {
        return input.getSpsContainer();
    }

    interface Input {
        Pane getSpsContainer();
        TimeController getTimer();
    }
}
