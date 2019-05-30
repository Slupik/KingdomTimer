package jw.kingdom.hall.kingdomtimer.webui.domain.screen;

import javafx.scene.layout.Region;
import javafx.stage.Window;

abstract class SizeBinder {

    public static void bindSize(Region source, Region dest) {
        bindWidth(source, dest);
        bindHeight(source, dest);
    }

    static void bindSize(Window source, Region dest) {
        bindWidth(source, dest);
        bindHeight(source, dest);
    }

    private static void bindWidth(Region source, Region dest) {
        dest.minWidthProperty().bind(source.widthProperty());
        dest.prefWidthProperty().bind(source.widthProperty());
        dest.maxWidthProperty().bind(source.widthProperty());
    }

    private static void bindWidth(Window source, Region dest) {
        dest.minWidthProperty().bind(source.widthProperty());
        dest.prefWidthProperty().bind(source.widthProperty());
        dest.maxWidthProperty().bind(source.widthProperty());
    }

    private static void bindHeight(Region source, Region dest) {
        dest.minHeightProperty().bind(source.heightProperty());
        dest.prefHeightProperty().bind(source.heightProperty());
        dest.maxHeightProperty().bind(source.heightProperty());
    }

    private static void bindHeight(Window source, Region dest) {
        dest.minHeightProperty().bind(source.heightProperty());
        dest.prefHeightProperty().bind(source.heightProperty());
        dest.maxHeightProperty().bind(source.heightProperty());
    }

    private SizeBinder(){}
}
