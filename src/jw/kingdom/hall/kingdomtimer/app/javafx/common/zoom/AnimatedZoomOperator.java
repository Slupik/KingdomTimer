package jw.kingdom.hall.kingdomtimer.app.javafx.common.zoom;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class AnimatedZoomOperator {

    private Timeline timeline;

    public AnimatedZoomOperator() {
        this.timeline = new Timeline(60);
    }

    public void zoom(Node node, double factor) {
        // determine scale
        double oldScale = node.getScaleX();
        double scale = oldScale * factor;

        // timeline that scales and moves the node
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(200), new KeyValue(node.scaleXProperty(), scale)),
                new KeyFrame(Duration.millis(200), new KeyValue(node.scaleYProperty(), scale))
        );
        timeline.play();
    }
}