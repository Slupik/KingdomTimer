package jw.kingdom.hall.kingdomtimer.device.external.timer.http;

import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.countdown.CountdownImpl;
import jw.kingdom.hall.kingdomtimer.domain.schedule.MeetingSchedule;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeController;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeControllerImpl;
import org.junit.jupiter.api.Test;

/**
 * All rights reserved & copyright ©
 */
public class ServerForHttpDisplaysImplTest {

    @Test
    public void sendCommand() {
        Countdown countdown = new CountdownImpl();
        Schedule schedule = new MeetingSchedule();
        TimeController time = new TimeControllerImpl(schedule, countdown);

        ServerForHttpDisplaysImpl server = new ServerForHttpDisplaysImpl(time);
        server.add("192.168.1.120");

        server.sendCommand("start,20");
    }
}