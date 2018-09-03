package jw.kingdom.hall.kingdomtimer.domain.backup;

import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdown;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.schedule.MeetingSchedule;

/**
 * All rights reserved & copyright ©
 */
class TimeBackupMaker {
    TimeBackupMaker(){
        MeetingSchedule.getInstance().addListener(new MeetingSchedule.Listener() {
            @Override
            public void onMeetingStart() {

            }

            @Override
            public void onNextTask(int index, MeetingTask task) {

            }

            @Override
            public void onMeetingListEnd() {

            }

            @Override
            public void onMeetingEnd() {

            }

            @Override
            public void onRemove(MeetingTask task) {

            }

            @Override
            public void onInsert(MeetingTask task) {

            }

            @Override
            public void onBulkInsert(MeetingTask... task) {

            }

            @Override
            public void onClear() {

            }
        });
    }
}
