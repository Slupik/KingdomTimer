package jw.kingdom.hall.kingdomtimer.javafx.utils;

import javafx.scene.layout.Region;
import javafx.stage.Window;

/**
 * All rights reserved & copyright ©
 */
public abstract class SizeBinder {

    public static void bindSize(Region source, Region dest) {
        bindWidth(source, dest);
        bindHeight(source, dest);
    }

    public static void bindSize(Window source, Region dest) {
        bindWidth(source, dest);
        bindHeight(source, dest);
    }

    public static void bindWidth(Region source, Region dest) {
        dest.minWidthProperty().bind(source.widthProperty());
        dest.prefWidthProperty().bind(source.widthProperty());
        dest.maxWidthProperty().bind(source.widthProperty());
    }

    public static void bindWidth(Window source, Region dest) {
        dest.minWidthProperty().bind(source.widthProperty());
        dest.prefWidthProperty().bind(source.widthProperty());
        dest.maxWidthProperty().bind(source.widthProperty());
    }

    public static void bindHeight(Region source, Region dest) {
        dest.minHeightProperty().bind(source.heightProperty());
        dest.prefHeightProperty().bind(source.heightProperty());
        dest.maxHeightProperty().bind(source.heightProperty());
    }

    public static void bindHeight(Window source, Region dest) {
        dest.minHeightProperty().bind(source.heightProperty());
        dest.prefHeightProperty().bind(source.heightProperty());
        dest.maxHeightProperty().bind(source.heightProperty());
    }

    private SizeBinder(){}
}
