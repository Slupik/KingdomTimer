package jw.kingdom.hall.kingdomtimer.javafx.control.sps;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownListener;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;

/**
 * All rights reserved & copyright ©
 */
public class SpsControllerForTime implements StartPauseStopView.Controller, StartPauseStopView.Listener {

    private final StartPauseStopView view;
    private final Input input;

    public SpsControllerForTime(Input input){
        this.view = new StartPauseStopView();
        this.input = input;
        view.addListener(this);
        view.setController(this);
        init();
    }

    private void init() {
        input.getCountdown().addListener(new CountdownListener() {
            @Override
            protected void onStart() {
                super.onStart();
                view.setupForStart();
            }

            @Override
            protected void onPause() {
                super.onPause();
                view.setupForPause();
            }

            @Override
            protected void onResume() {
                super.onResume();
                view.setupForUnPause();
            }

            @Override
            protected void onStop() {
                super.onStop();
                view.setupForStop();
            }
        });
    }

    @Override
    public boolean isToExecuteSPSAction(StartPauseStopView.ActionType type) {
        if(type==StartPauseStopView.ActionType.START) {
            return input.getSchedule().getTasks().size()!=0;
        }
        return true;
    }

    @Override
    public void onStart() {
        Task task = input.getSchedule().bringOutFirstTask();
        input.getCountdown().start(task);
    }

    @Override
    public void onPause() {
        input.getCountdown().pause();
    }

    @Override
    public void onResume() {
        input.getCountdown().resume();
    }

    @Override
    public void onStop() {
        input.getCountdown().stop();
    }

    public StartPauseStopView getView() {
        return view;
    }

    public interface Input {
        ScheduleController getSchedule();
        CountdownController getCountdown();
    }
}
