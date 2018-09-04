package jw.kingdom.hall.kingdomtimer.log;

import java.io.*;

/**
 * All rights reserved & copyright ©
 */
public class SysErrStream extends PrintStream {
    public SysErrStream(OutputStream out) {
        super(out);
    }

    public SysErrStream(OutputStream out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public SysErrStream(OutputStream out, boolean autoFlush, String encoding) throws UnsupportedEncodingException {
        super(out, autoFlush, encoding);
    }

    public SysErrStream(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public SysErrStream(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(fileName, csn);
    }

    public SysErrStream(File file) throws FileNotFoundException {
        super(file);
    }

    public SysErrStream(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(file, csn);
    }

    @Override
    public void println(String x) {
        super.println(x);
        Log.e(x);
    }

    @Override
    public void print(String s) {
        super.print(s);
        if (s == null) {
            s = "null";
        }
        Log.e(s);
    }
}
