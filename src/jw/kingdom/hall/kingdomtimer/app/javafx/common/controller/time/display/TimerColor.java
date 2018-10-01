package jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.time.display;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class TimerColor {
    private final static int NORMAL = 0;
    private final static int WARNING = 1;
    private final static int END_OF_TIME = 2;

    /**
     * @param maxTime Total amount of available time at the start
     * @param time Actual time in direct down mode (only)
     * @return Code of color. Color can be get by function getColor
     */
    static int getColorCode(int maxTime, int time){
        if(time <= 0) {
            return END_OF_TIME;
        }
        if (time <= getWarningTime(maxTime)) {
            return WARNING;
        }
        return getDefaultColorCode();
    }

    static int getDefaultColorCode(){
        return NORMAL;
    }

    private static int getWarningTime(int maxTime) {
        int returnTime = (int) (maxTime*0.1);
        if(returnTime <= 5) {
            return 5;
        } else {
            return returnTime;
        }
    }

    static Paint getColor(int code, boolean isLightBackground) {
        switch (code) {
            case END_OF_TIME: {
                if(isLightBackground) {
                    return Color.RED;
                } else {
//                    return Color.ORANGERED;
                    return Color.rgb(255, 97, 97);
                }
            }
            case WARNING: {
                if(isLightBackground) {
                    return Color.ORANGE;
                } else {
                    return Color.YELLOW;
                }
            }
            default: {
                if(isLightBackground) {
                    return Color.BLACK;
                } else {
                    return Color.WHITE;
                }
            }
        }
    }
}
