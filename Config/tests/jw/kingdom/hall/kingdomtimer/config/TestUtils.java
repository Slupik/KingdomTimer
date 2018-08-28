package jw.kingdom.hall.kingdomtimer.config;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * All rights reserved & copyright ©
 */
public class TestUtils {
    private static PrintStream err;
    public static void muteErrors(){
        err = System.err;
        System.setErr(new PrintStream(new ByteArrayOutputStream()));
    }
    public static void unmuteErrors(){
        System.setOut(err);
    }
}
