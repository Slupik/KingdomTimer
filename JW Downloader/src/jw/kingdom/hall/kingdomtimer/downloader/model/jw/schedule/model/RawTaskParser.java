
/*
 * Created 05.09.18 01:16.
 * Last modified 05.09.18 01:16
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model;

import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;

class RawTaskParser {
    static ScheduleTask getParsed(String elementSu) throws WrongElementException {
        ScheduleTask task = new ScheduleTask();
        task.setName(getTitle(elementSu));
        task.setTime(getTime(elementSu));
        return task;
    }

    private static int getTime(String source) throws WrongElementException {
        if(source.split(getLeftDelimiter(source)).length<2) {
            throw new WrongElementException();
        }
        int seconds = -1;
        int index = 0;
        while(seconds<0) {
            String raw = source.split(getLeftDelimiter(source))[index+1];
            raw = raw.split(getRightDelimiter(source))[0];
            raw = raw.replaceAll("[^\\d]", "");
            if(raw.length()>0) {
                seconds = Integer.parseInt(raw)*60;
            } else {
                index++;
            }
        }
        return seconds;
    }

    private static String getLeftDelimiter(String source) {
        if(source.contains("(")) {
            return "\\(";
        } else {
            return "（";//Chinese
        }
    }

    private static String getRightDelimiter(String source) {
        if(source.contains(")")) {
            return "\\)";
        } else {
            return "）";//Chinese
        }
    }

    private static String getTitle(String source) {
        String raw = source.split("\\(")[0];
        raw = removeLast(raw, ":");
        raw = raw.trim();
        return raw;
    }

    private static String removeLast(String text, String toEliminate) {
        if(text.contains(String.valueOf(toEliminate))) {
            StringBuilder builder = new StringBuilder(text);
            builder.replace(text.lastIndexOf(toEliminate), text.lastIndexOf(toEliminate) + toEliminate.length(), "" );
            return builder.toString();
        } else {
            return text;
        }
    }
}
