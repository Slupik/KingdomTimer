package jw.kingdom.hall.kingdomtimer.log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * All rights reserved & copyright ©
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
