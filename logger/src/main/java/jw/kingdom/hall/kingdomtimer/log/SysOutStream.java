package jw.kingdom.hall.kingdomtimer.log;

import java.io.*;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class SysOutStream extends PrintStream {
    public SysOutStream(OutputStream out) {
        super(out);
    }

    public SysOutStream(OutputStream out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public SysOutStream(OutputStream out, boolean autoFlush, String encoding) throws UnsupportedEncodingException {
        super(out, autoFlush, encoding);
    }

    public SysOutStream(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public SysOutStream(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(fileName, csn);
    }

    public SysOutStream(File file) throws FileNotFoundException {
        super(file);
    }

    public SysOutStream(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(file, csn);
    }

    @Override
    public void println(String x) {
        super.println(x);
        Log.v(x);
    }

    @Override
    public void print(String s) {
        super.print(s);
        if (s == null) {
            s = "null";
        }
        Log.v(s);
    }
}
