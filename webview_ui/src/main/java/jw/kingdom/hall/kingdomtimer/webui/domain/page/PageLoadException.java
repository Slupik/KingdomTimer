package jw.kingdom.hall.kingdomtimer.webui.domain.page;

/**
 * All rights reserved & copyright ©
 */
public class PageLoadException extends Exception {

    PageLoadException(Exception e) {
        super(e);
    }

    public String getAsText() {
        StringBuilder output = new StringBuilder();

        output.append(toString());
        StackTraceElement[] trace = getStackTrace();
        for (StackTraceElement traceElement : trace)
            output.append("\tat ").append(traceElement);

        return output.toString();
    }
}
