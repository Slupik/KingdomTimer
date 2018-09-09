package jw.kingdom.hall.kingdomtimer.javafx.control.sps;

import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class SpsControllerForRecord implements StartPauseStopView.Controller, StartPauseStopView.Listener {
    private final StartPauseStopView view;
    private final Input input;

    public SpsControllerForRecord(Input input){
        this.view = new StartPauseStopView();
        this.input = input;
        view.addListener(this);
        view.setController(this);
        init();
    }

    private void init() {
        input.getRecorder();
    }

    @Override
    public boolean isToExecuteSPSAction(StartPauseStopView.ActionType type) {
        return true;
    }

    @Override
    public void onStart() {
        input.getRecorder().onStart();
    }

    @Override
    public void onPause() {
        input.getRecorder().setPause(true);
    }

    @Override
    public void onResume() {
        input.getRecorder().setPause(false);
    }

    @Override
    public void onStop() {
        input.getRecorder().onStop();
    }

    public StartPauseStopView getView() {
        return view;
    }

    public interface Input {
        Recorder getRecorder();
    }
}
