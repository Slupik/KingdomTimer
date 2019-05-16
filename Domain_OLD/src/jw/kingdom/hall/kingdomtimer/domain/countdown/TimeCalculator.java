package jw.kingdom.hall.kingdomtimer.domain.countdown;

/**
 * All rights reserved & copyright ©
 */
abstract class TimeCalculator {

    static int getDisplayTime(int max, int actual, boolean isDirectDown) {
        if(isDirectDown) {
            return actual;
        } else {
            if(actual<0){
                return actual;
            } else {
                return max-actual;
            }
        }
    }
}
