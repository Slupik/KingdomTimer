package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.record;

import jw.kingdom.hall.kingdomtimer.javafx.control.sps.StartPauseStopView;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

/**
 * All rights reserved & copyright ©
 */
class RecordPanelPresenter implements StartPauseStopView.Listener, StartPauseStopView.Controller {

    private final StartPauseStopView view;

    private final Input input;

    RecordPanelPresenter(Input input){
        this.view = new StartPauseStopView();
        this.input = input;
        init();
    }

    private void init() {
        getRecorder().addListener(new Recorder.Listener() {
            @Override
            public void onStop() {
                view.setupForStop();
            }

            @Override
            public void onPause() {
                view.setupForPause();
            }

            @Override
            public void onResume() {
                view.setupForUnPause();
            }

            @Override
            public void onStart() {
                view.setupForStart();
            }
        });
    }

    @Override
    public void onStop() {
        getRecorder().onStop();
    }

    @Override
    public void onPause() {
        getRecorder().setPause(true);
    }

    @Override
    public void onResume() {
        getRecorder().setPause(false);
    }

    @Override
    public void onStart() {
        getRecorder().onStart();
    }

    @Override
    public boolean isToExecuteSPSAction(StartPauseStopView.ActionType type) {
        return true;
    }

    public StartPauseStopView getView() {
        return view;
    }

    private Recorder getRecorder() {
        return input.getRecorder();
    }

    interface Input {
        Recorder getRecorder();
    }
}
