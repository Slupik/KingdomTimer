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
    private final Data data;

    public SpsControllerForTime(Data data){
        this.view = new StartPauseStopView();
        this.data = data;
        view.addListener(this);
        view.setController(this);
        init();
    }

    private void init() {
        data.getCountdown().addListener(new CountdownListener() {
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
            return data.getSchedule().getTasks().size()!=0;
        }
        return true;
    }

    @Override
    public void onStart() {
        Task task = data.getSchedule().bringOutFirstTask();
        data.getCountdown().start(task);
    }

    @Override
    public void onPause() {
        data.getCountdown().pause();
    }

    @Override
    public void onResume() {
        data.getCountdown().resume();
    }

    @Override
    public void onStop() {
        data.getCountdown().stop();
    }

    public StartPauseStopView getView() {
        return view;
    }

    public interface Data {
        ScheduleController getSchedule();
        CountdownController getCountdown();
    }
}
