package jw.kingdom.hall.kingdomtimer.javafx.control.sps;

import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class SpsControllerForRecord implements StartPauseStopView.Controller, StartPauseStopView.Listener {
    private final StartPauseStopView view;
    private final Input input;
    private boolean ignore = false;

    public SpsControllerForRecord(Input input){
        this.view = new StartPauseStopView();
        this.input = input;
        view.addListener(this);
        view.setController(this);
        init();
    }

    private void init() {
        input.getRecorder();
        input.getRecorder().addListener(new Recorder.Listener() {
            @Override
            public void onStop() {
                ignore = true;
                view.setupForStop();
                ignore = false;
//                new Thread(()->{
//                    PlatformUtils.runOnUiThread(()->{
//                        System.out.println("STOP");
//                        view.setupForStop();
//                        ignore = false;
//                    });
//                }).start();
            }

            @Override
            public void onPause() {
                ignore = true;
                view.setupForPause();
                ignore = false;
            }

            @Override
            public void onResume() {
                ignore = true;
                view.setupForUnPause();
                ignore = false;
            }

            @Override
            public void onStart() {
                ignore = true;
                view.setupForStart();
                ignore = false;
            }
        });
    }

    @Override
    public boolean isToExecuteSPSAction(StartPauseStopView.ActionType type) {
        return true;
    }

    @Override
    public void onStart() {
        if(!ignore) {
            input.getRecorder().onStart();
        }
    }

    @Override
    public void onPause() {
        if(!ignore) {
            input.getRecorder().setPause(true);
        }
    }

    @Override
    public void onResume() {
        if(!ignore) {
            input.getRecorder().setPause(false);
        }
    }

    @Override
    public void onStop() {
        if(!ignore) {
            input.getRecorder().onStop();
        }
    }

    public StartPauseStopView getView() {
        return view;
    }

    public interface Input {
        Recorder getRecorder();
    }
}
