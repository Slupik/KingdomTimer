package jw.kingdom.hall.kingdomtimer.config;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
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
