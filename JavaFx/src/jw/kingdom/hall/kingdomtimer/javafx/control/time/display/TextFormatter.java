package jw.kingdom.hall.kingdomtimer.javafx.control.time.display;

/**
 * All rights reserved & copyright ©
 */
abstract class TextFormatter {
    static String secondsToText(int time) {
        boolean isSmallerThanZero = time<0;
        if(isSmallerThanZero) {
            time = Math.abs(time);
        }
        int hours = time/3600;
        int minutes = (time%3600)/60;
        int seconds = time%60;
        String basic = getFormattedNumber(hours)+":"+getFormattedNumber(minutes)+":"+getFormattedNumber(seconds);
        if(basic.startsWith("00:")) {
            basic = basic.substring(3);
        }
        if(isSmallerThanZero) {
            return "-"+basic;
        } else {
            return basic;
        }
    }

    private static String getFormattedNumber(int number) {
        if(number<10) {
            return "0"+Integer.toString(number);
        }
        return Integer.toString(number);
    }

    private TextFormatter(){}
}
