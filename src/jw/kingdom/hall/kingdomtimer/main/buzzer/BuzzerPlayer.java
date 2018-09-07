package jw.kingdom.hall.kingdomtimer.main.buzzer;

import jw.kingdom.hall.kingdomtimer.device.sound.Buzzer;

/**
 * All rights reserved & copyright ©
 */
public class BuzzerPlayer implements jw.kingdom.hall.kingdomtimer.entity.time.buzzer.BuzzerPlayer {
    @Override
    public void play() {
        new Thread(Buzzer::play).start();
    }
}
