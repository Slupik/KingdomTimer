package jw.kingdom.hall.kingdomtimer.javafx.custom;

import java.sql.SQLOutput;

/**
 * All rights reserved & copyright ©
 */
public class TimeFieldUtils {

    static String getFormattedText(String oldText, String text) {
        try {
            String[] elements = text.split(":");
            return getFormattedElement(elements[0])+":"+getFormattedElement(elements[1])+":"+getFormattedElement(elements[2]);
        } catch (Exception e) {
            //ex. ArrayIndexOutOfBoundsException if is less than 3 characters like ":"
            return oldText;
        }
    }

    static String getFormattedElement(String text) {
        if(null==text || text.length()==0){
            return "00";
        }
        if(1 == text.length()) {
            if(isNormalInt(text)) {
                return "0"+text;
            } else {
                return "00";
            }
        } else if (2 == text.length()) {
            if(isNormalInt(text)) {
                return text;
            } else {
                return "00";
            }
        } else {
            text = removeNonDigitChars(text);
            if(text.length()<3) return getFormattedElement(text);

            text = trimZeros(text);
            if(text.length()<3) return getFormattedElement(text);

            return text.substring(0, 2);
        }
    }

    static String removeNonDigitChars(String text) {
        StringBuilder newText = new StringBuilder();
        for(int i=0;i<text.length();i++) {
            char element = text.charAt(i);
            if(isNormalInt(Character.toString(element))) {
                newText.append(element);
            }
        }
        return newText.toString();
    }

    static String trimZeros(String text) {
        text = trimZeroOnLeft(text);
        return trimZeroOnRight(text);
    }

    static String trimZeroOnRight(String text) {
        StringBuilder newText = new StringBuilder();
        boolean startAdding = false;

        for(int i=text.length()-1;i>=0;i--) {
            char element = text.charAt(i);
            if(element!='0' && !startAdding) {
                startAdding = true;
            }
            if(startAdding || (text.length()-i+1+newText.length())<3) {
                newText.append(element);
            }
        }
        newText.reverse();
        return newText.toString();
    }

    static String trimZeroOnLeft(String text) {
        StringBuilder newText = new StringBuilder();
        boolean startAdding = false;

        for(int i=0;i<text.length();i++) {
            char element = text.charAt(i);
            if(element!='0' && !startAdding) {
                startAdding = true;
            }
            if(startAdding) {
                newText.append(element);
            }
        }
        return newText.toString();
    }

    static boolean isTextNormal(String text) {
        return -1 != getAllSeconds(text);
    }

    public static int getAllSeconds(String text) {
        String[] parts = text.split(":");
        if(parts.length!=3) {
            return -1;
        }
        for(String part:parts) {
            if(part.length()>2){
                return -1;
            }
            if(0 == part.length()){
                return -1;
            }
        }
        int seconds = 0;
        try {
            seconds += Integer.parseInt(parts[0])*3600;
            seconds += Integer.parseInt(parts[1])*60;
            seconds += Integer.parseInt(parts[2]);
        } catch (NumberFormatException e){
            return -1;
        }
        return seconds;
    }

    static boolean isNormalInt(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /*
    FROM SECONDS TO TEXT
     */

    static String secondsToText(int time) {
        boolean isSmallerThanZero = time<0;
        if(isSmallerThanZero) {
            time = Math.abs(time);
        }
        int hours = time/3600;
        int minutes = (time%3600)/60;
        int seconds = time%60;
        String basic = getFormattedNumber(hours)+":"+getFormattedNumber(minutes)+":"+getFormattedNumber(seconds);
        if(isSmallerThanZero) {
            return "-"+basic;
        } else {
            return basic;
        }
    }

    static String getFormattedNumber(int number) {
        if(number<10) {
            return "0"+Integer.toString(number);
        }
        return Integer.toString(number);
    }
}
