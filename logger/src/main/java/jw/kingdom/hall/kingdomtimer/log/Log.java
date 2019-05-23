package jw.kingdom.hall.kingdomtimer.log;

import com.google.gson.Gson;

import java.io.PrintStream;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class Log {
    private static LogSaver saver;
    private static PrintStream sysErr;
    private static PrintStream sysOut;

    public static void init(LogFileProvider file){
        if(saver==null) {
            saver = new LogSaver(file);
            saver.printHeader();
            sysErr = new SysErrStream(System.err);
            sysOut = new SysOutStream(System.out);
            System.setErr(sysErr);
            System.setOut(sysOut);
        }
    }

    protected static void save(LogLevel lvl, String content) {
        save(lvl, "", content);
    }

    protected static void save(LogLevel lvl, String tag, String content) {
        saver.save(lvl, tag, content);
    }

    /**
     * ==================================================
     *
     *                 Boilerplate below
     *
     *===================================================
     * /

    /*
        Debug
     */
    public static void dObj(Object obj) {
        log(LogLevel.DEBUG, "", new Gson().toJson(obj));
    }
    public static void d(Object value) {
        log(LogLevel.DEBUG, "", value);
    }
    public static void d(boolean value) {
        log(LogLevel.DEBUG, "", value);
    }
    public static void d(char value) {
        log(LogLevel.DEBUG, "", value);
    }
    public static void d(short value) {
        log(LogLevel.DEBUG, "", value);
    }
    public static void d(int value) {
        log(LogLevel.DEBUG, "", value);
    }
    public static void d(float value) {
        log(LogLevel.DEBUG, "", value);
    }
    public static void d(long value) {
        log(LogLevel.DEBUG, "", value);
    }
    public static void d(double value) {
        log(LogLevel.DEBUG, "", value);
    }
    public static void d(String value) {
        save(LogLevel.DEBUG, "", value);
    }
    /*
        Debug TAG
     */
    public static void dObj(String tag, Object obj) {
        log(LogLevel.DEBUG, new Gson().toJson(obj));
    }
    public static void d(String tag, Object value) {
        log(LogLevel.DEBUG, tag, value);
    }
    public static void d(String tag, boolean value) {
        log(LogLevel.DEBUG, tag, value);
    }
    public static void d(String tag, char value) {
        log(LogLevel.DEBUG, tag, value);
    }
    public static void d(String tag, short value) {
        log(LogLevel.DEBUG, tag, value);
    }
    public static void d(String tag, int value) {
        log(LogLevel.DEBUG, tag, value);
    }
    public static void d(String tag, float value) {
        log(LogLevel.DEBUG, tag, value);
    }
    public static void d(String tag, long value) {
        log(LogLevel.DEBUG, tag, value);
    }
    public static void d(String tag, double value) {
        log(LogLevel.DEBUG, tag, value);
    }
    public static void d(String tag, String value) {
        save(LogLevel.DEBUG, tag, value);
    }
    
    /*
        Verbose
     */
    public static void vObj(String tag, Object obj) {
        log(LogLevel.VERBOSE, "", new Gson().toJson(obj));
    }
    public static void v(Object value) {
        log(LogLevel.VERBOSE, "", value);
    }
    public static void v(boolean value) {
        log(LogLevel.VERBOSE, "", value);
    }
    public static void v(char value) {
        log(LogLevel.VERBOSE, "", value);
    }
    public static void v(short value) {
        log(LogLevel.VERBOSE, "", value);
    }
    public static void v(int value) {
        log(LogLevel.VERBOSE, "", value);
    }
    public static void v(float value) {
        log(LogLevel.VERBOSE, "", value);
    }
    public static void v(long value) {
        log(LogLevel.VERBOSE, "", value);
    }
    public static void v(double value) {
        log(LogLevel.VERBOSE, "", value);
    }
    public static void v(String value) {
        save(LogLevel.VERBOSE, "", value);
    }  
    /*
        Verbose TAG
     */
    public static void vObj(Object obj) {
        log(LogLevel.VERBOSE, new Gson().toJson(obj));
    }
    public static void v(String tag, Object value) {
        log(LogLevel.VERBOSE, tag, value);
    }
    public static void v(String tag, boolean value) {
        log(LogLevel.VERBOSE, tag, value);
    }
    public static void v(String tag, char value) {
        log(LogLevel.VERBOSE, tag, value);
    }
    public static void v(String tag, short value) {
        log(LogLevel.VERBOSE, tag, value);
    }
    public static void v(String tag, int value) {
        log(LogLevel.VERBOSE, tag, value);
    }
    public static void v(String tag, float value) {
        log(LogLevel.VERBOSE, tag, value);
    }
    public static void v(String tag, long value) {
        log(LogLevel.VERBOSE, tag, value);
    }
    public static void v(String tag, double value) {
        log(LogLevel.VERBOSE, tag, value);
    }
    public static void v(String tag, String value) {
        save(LogLevel.VERBOSE, tag, value);
    }
    
    /*
        Info
     */
    public static void iObj(String tag, Object obj) {
        log(LogLevel.INFO, "", new Gson().toJson(obj));
    }
    public static void i(Object value) {
        log(LogLevel.INFO, "", value);
    }
    public static void i(boolean value) {
        log(LogLevel.INFO, "", value);
    }
    public static void i(char value) {
        log(LogLevel.INFO, "", value);
    }
    public static void i(short value) {
        log(LogLevel.INFO, "", value);
    }
    public static void i(int value) {
        log(LogLevel.INFO, "", value);
    }
    public static void i(float value) {
        log(LogLevel.INFO, "", value);
    }
    public static void i(long value) {
        log(LogLevel.INFO, "", value);
    }
    public static void i(double value) {
        log(LogLevel.INFO, "", value);
    }
    public static void i(String value) {
        save(LogLevel.INFO, "", value);
    }
    /*
        Info TAG
     */
    public static void iObj(Object obj) {
        log(LogLevel.INFO, new Gson().toJson(obj));
    }
    public static void i(String tag, Object value) {
        log(LogLevel.INFO, tag, value);
    }
    public static void i(String tag, boolean value) {
        log(LogLevel.INFO, tag, value);
    }
    public static void i(String tag, char value) {
        log(LogLevel.INFO, tag, value);
    }
    public static void i(String tag, short value) {
        log(LogLevel.INFO, tag, value);
    }
    public static void i(String tag, int value) {
        log(LogLevel.INFO, tag, value);
    }
    public static void i(String tag, float value) {
        log(LogLevel.INFO, tag, value);
    }
    public static void i(String tag, long value) {
        log(LogLevel.INFO, tag, value);
    }
    public static void i(String tag, double value) {
        log(LogLevel.INFO, tag, value);
    }
    public static void i(String tag, String value) {
        save(LogLevel.INFO, tag, value);
    }
    
    /*
        Warning
     */
    public static void wObj(String tag, Object obj) {
        log(LogLevel.WARN, "", new Gson().toJson(obj));
    }
    public static void w(Object value) {
        log(LogLevel.WARN, "", value);
    }
    public static void w(boolean value) {
        log(LogLevel.WARN, "", value);
    }
    public static void w(char value) {
        log(LogLevel.WARN, "", value);
    }
    public static void w(short value) {
        log(LogLevel.WARN, "", value);
    }
    public static void w(int value) {
        log(LogLevel.WARN, "", value);
    }
    public static void w(float value) {
        log(LogLevel.WARN, "", value);
    }
    public static void w(long value) {
        log(LogLevel.WARN, "", value);
    }
    public static void w(double value) {
        log(LogLevel.WARN, "", value);
    }
    public static void w(String value) {
        save(LogLevel.WARN, "", value);
    }    
    /*
        Warning TAG
     */
    public static void wObj(Object obj) {
        log(LogLevel.WARN, new Gson().toJson(obj));
    }
    public static void w(String tag, Object value) {
        log(LogLevel.WARN, tag, value);
    }
    public static void w(String tag, boolean value) {
        log(LogLevel.WARN, tag, value);
    }
    public static void w(String tag, char value) {
        log(LogLevel.WARN, tag, value);
    }
    public static void w(String tag, short value) {
        log(LogLevel.WARN, tag, value);
    }
    public static void w(String tag, int value) {
        log(LogLevel.WARN, tag, value);
    }
    public static void w(String tag, float value) {
        log(LogLevel.WARN, tag, value);
    }
    public static void w(String tag, long value) {
        log(LogLevel.WARN, tag, value);
    }
    public static void w(String tag, double value) {
        log(LogLevel.WARN, tag, value);
    }
    public static void w(String tag, String value) {
        save(LogLevel.WARN, tag, value);
    }
    
    /*
        Error
     */
    public static void eObj(String tag, Object obj) {
        log(LogLevel.ERROR, "", new Gson().toJson(obj));
    }
    public static void e(Object value) {
        log(LogLevel.ERROR, "", value);
    }
    public static void e(boolean value) {
        log(LogLevel.ERROR, "", value);
    }
    public static void e(char value) {
        log(LogLevel.ERROR, "", value);
    }
    public static void e(short value) {
        log(LogLevel.ERROR, "", value);
    }
    public static void e(int value) {
        log(LogLevel.ERROR, "", value);
    }
    public static void e(float value) {
        log(LogLevel.ERROR, "", value);
    }
    public static void e(long value) {
        log(LogLevel.ERROR, "", value);
    }
    public static void e(double value) {
        log(LogLevel.ERROR, "", value);
    }
    public static void e(String value) {
        save(LogLevel.ERROR, "", value);
    }
    /*
        Error TAG
     */
    public static void eObj(Object obj) {
        log(LogLevel.ERROR, new Gson().toJson(obj));
    }
    public static void e(String tag, Object value) {
        log(LogLevel.ERROR, tag, value);
    }
    public static void e(String tag, boolean value) {
        log(LogLevel.ERROR, tag, value);
    }
    public static void e(String tag, char value) {
        log(LogLevel.ERROR, tag, value);
    }
    public static void e(String tag, short value) {
        log(LogLevel.ERROR, tag, value);
    }
    public static void e(String tag, int value) {
        log(LogLevel.ERROR, tag, value);
    }
    public static void e(String tag, float value) {
        log(LogLevel.ERROR, tag, value);
    }
    public static void e(String tag, long value) {
        log(LogLevel.ERROR, tag, value);
    }
    public static void e(String tag, double value) {
        log(LogLevel.ERROR, tag, value);
    }
    public static void e(String tag, String value) {
        save(LogLevel.ERROR, tag, value);
    }
    
    /*
        What a Terrible Failure
     */
    public static void wtfObj(Object obj) {
        log(LogLevel.WTF, "", new Gson().toJson(obj));
    }
    public static void wtf(Object value) {
        log(LogLevel.WTF, "", value);
    }
    public static void wtf(boolean value) {
        log(LogLevel.WTF, "", value);
    }
    public static void wtf(char value) {
        log(LogLevel.WTF, "", value);
    }
    public static void wtf(short value) {
        log(LogLevel.WTF, "", value);
    }
    public static void wtf(int value) {
        log(LogLevel.WTF, "", value);
    }
    public static void wtf(float value) {
        log(LogLevel.WTF, "", value);
    }
    public static void wtf(long value) {
        log(LogLevel.WTF, "", value);
    }
    public static void wtf(double value) {
        log(LogLevel.WTF, "", value);
    }
    public static void wtf(String value) {
        save(LogLevel.WTF, "", value);
    }    /*
        What a Terrible Failure TAG
     */
    public static void wtfObj(String tag, Object obj) {
        log(LogLevel.WTF, tag, new Gson().toJson(obj));
    }
    public static void wtf(String tag, Object value) {
        log(LogLevel.WTF, tag, value);
    }
    public static void wtf(String tag, boolean value) {
        log(LogLevel.WTF, tag, value);
    }
    public static void wtf(String tag, char value) {
        log(LogLevel.WTF, tag, value);
    }
    public static void wtf(String tag, short value) {
        log(LogLevel.WTF, tag, value);
    }
    public static void wtf(String tag, int value) {
        log(LogLevel.WTF, tag, value);
    }
    public static void wtf(String tag, float value) {
        log(LogLevel.WTF, tag, value);
    }
    public static void wtf(String tag, long value) {
        log(LogLevel.WTF, tag, value);
    }
    public static void wtf(String tag, double value) {
        log(LogLevel.WTF, tag, value);
    }
    public static void wtf(String tag, String value) {
        save(LogLevel.WTF, tag, value);
    }
    
    /*
        Standard
     */
    public static void logObj(LogLevel lvl, Object obj) {
        log(lvl, "", new Gson().toJson(obj));
    }
    public static void log(LogLevel lvl, Object value) {
        log(lvl, "", String.valueOf(value));
    }
    public static void log(LogLevel lvl, boolean value) {
        log(lvl, "", String.valueOf(value));
    }
    public static void log(LogLevel lvl, char value) {
        log(lvl, "", String.valueOf(value));
    }
    public static void log(LogLevel lvl, short value) {
        log(lvl, "", String.valueOf(value));
    }
    public static void log(LogLevel lvl, int value) {
        log(lvl, "", String.valueOf(value));
    }
    public static void log(LogLevel lvl, float value) {
        log(lvl, "", String.valueOf(value));
    }
    public static void log(LogLevel lvl, long value) {
        log(lvl, "", String.valueOf(value));
    }
    public static void log(LogLevel lvl, double value) {
        log(lvl, "", String.valueOf(value));
    }
    public static void log(LogLevel lvl, String value) {
        save(lvl, "", value);
    }    /*
        Standard TAG
     */
    public static void logObj(LogLevel lvl, String tag, Object obj) {
        log(lvl, tag, new Gson().toJson(obj));
    }
    public static void log(LogLevel lvl, String tag, Object value) {
        log(lvl, tag, String.valueOf(value));
    }
    public static void log(LogLevel lvl, String tag, boolean value) {
        log(lvl, tag, String.valueOf(value));
    }
    public static void log(LogLevel lvl, String tag, char value) {
        log(lvl, tag, String.valueOf(value));
    }
    public static void log(LogLevel lvl, String tag, short value) {
        log(lvl, tag, String.valueOf(value));
    }
    public static void log(LogLevel lvl, String tag, int value) {
        log(lvl, tag, String.valueOf(value));
    }
    public static void log(LogLevel lvl, String tag, float value) {
        log(lvl, tag, String.valueOf(value));
    }
    public static void log(LogLevel lvl, String tag, long value) {
        log(lvl, tag, String.valueOf(value));
    }
    public static void log(LogLevel lvl, String tag, double value) {
        log(lvl, tag, String.valueOf(value));
    }
    public static void log(LogLevel lvl, String tag, String value) {
        save(lvl, tag, value);
    }
    
    private Log(){}
}
