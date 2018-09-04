package jw.kingdom.hall.kingdomtimer.log;

/**
 * All rights reserved & copyright ©
 */
//Inspirited by https://developer.android.com/reference/android/util/Log
public enum LogLevel {
    WTF(0, "WTF"),//What a Terrible Failure - presents exception that should never happen
    ERROR(1, "ERROR"),//ex from System.err
    WARN(2, "WARN"),//if something is not ok, but it's not an error
    INFO(3, "INFO"),//info about interesting parts of program ex. paths
    VERBOSE(4, "VERBOSE"),//everything what is boring :) ex. initialization of program
    DEBUG(5, "DEBUG"),//for debug purposes only
    ;

    public final int ID;
    public final String NAME;

    LogLevel(int id, String name) {
        ID = id;
        NAME = name;
    }
}
