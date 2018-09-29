package jw.kingdom.hall.kingdomtimer.device.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import jw.kingdom.hall.kingdomtimer.domain.buzzer.BuzzerPlayer;

import java.net.URISyntaxException;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class Buzzer implements BuzzerPlayer {

    @Override
    public void play(){
        try {
            final Media media = new Media(Buzzer.class.getResource("/sounds/bell.mp3").toURI().toString());
            final MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
