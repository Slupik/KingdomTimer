package jw.kingdom.hall.kingdomtimer.domain.clock;

import jw.kingdom.hall.kingdomtimer.domain.time.TimeDisplay;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * All rights reserved & copyright ©
 */
public interface Clock {
    default void start() {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        int hour  = calendar.get(Calendar.HOUR_OF_DAY);
        int minute  = calendar.get(Calendar.MINUTE);
        int seconds  = calendar.get(Calendar.SECOND);
        start(hour*3600+minute*60+seconds);
    }
    void start(int startValue);

    void stop();

    void addDisplay(TimeDisplay display);
    void removeDisplay(TimeDisplay display);
}
