package jw.kingdom.hall.kingdomtimer.device.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;

/**
 * All rights reserved & copyright ©
 */
public class Buzzer {
    public static void play(){
        try {
            final Media media = new Media(Buzzer.class.getResource("/sounds/bell.mp3").toURI().toString());
            final MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
