package jw.kingdom.hall.kingdomtimer.log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public abstract class LogHeader {
    private static final String ENTER = System.getProperty("line.separator");

    public static String getText(){
        String time = "["+getTime()+"]";
        return "[HEADER]"+time+": "+"Start"+ENTER;
    }

    private static String getTime() {
        return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS").format(new Date());
    }

    private LogHeader(){}
}
