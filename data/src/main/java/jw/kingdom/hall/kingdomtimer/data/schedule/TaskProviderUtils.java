/*
 * Feel free to use
 */

package jw.kingdom.hall.kingdomtimer.data.schedule;

import java.util.Calendar;
import java.util.Date;

abstract class TaskProviderUtils {

    static boolean isWeekend(long forTimeInMillis) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date(forTimeInMillis));
        return (cl.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                cl.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }

}
