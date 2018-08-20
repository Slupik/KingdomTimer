package jw.kingdom.hall.kingdomtimer.domain.countdown;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import sun.rmi.runtime.Log;

/**
 * All rights reserved & copyright ©
 */
public class TimerColor {
    private final static int NORMAL = 0;
    private final static int WARNING = 1;
    private final static int END_OF_TIME = 2;

    static int getColorCode(int maxTime, boolean isDirectDown, int time){
        if(!isDirectDown) {
            time = maxTime-time;
        }
        if(time <= 0) {
            return END_OF_TIME;
        }
        if (time <= getWarningTime(maxTime)) {
            return WARNING;
        }
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

    public static Paint getColor(int code, boolean isLightBackground) {
        switch (code) {
            case END_OF_TIME: {
                return Color.RED;
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
