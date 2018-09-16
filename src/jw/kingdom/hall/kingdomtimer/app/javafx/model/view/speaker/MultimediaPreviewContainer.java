package jw.kingdom.hall.kingdomtimer.app.javafx.model.view.speaker;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public interface MultimediaPreviewContainer {
    Region getMainContainer();
    VBox getMultimediaContainer();
    ImageView getView();
}
