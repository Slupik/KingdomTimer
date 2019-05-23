package jw.kingdom.hall.kingdomtimer.javafx.common.controller.time.display;

/**
 * All rights reserved & copyright ©
 */
abstract class TextFormatter {

    static String getFormattedTime(int timeInSeconds) {
        boolean isSmallerThanZero = timeInSeconds<0;
        if(isSmallerThanZero) {
            timeInSeconds = Math.abs(timeInSeconds);
        }
        int hours = timeInSeconds/3600;
        int minutes = (timeInSeconds%3600)/60;
        int seconds = timeInSeconds%60;
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
