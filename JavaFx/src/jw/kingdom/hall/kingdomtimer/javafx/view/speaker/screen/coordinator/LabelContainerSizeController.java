package jw.kingdom.hall.kingdomtimer.javafx.view.speaker.screen.coordinator;

import com.sun.javafx.tk.Toolkit;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import jw.kingdom.hall.kingdomtimer.javafx.utils.FontUtils;

import static jw.kingdom.hall.kingdomtimer.javafx.view.speaker.screen.coordinator.LabelVersion.SMALL;
import static jw.kingdom.hall.kingdomtimer.javafx.view.speaker.screen.coordinator.LabelVersion.UNKNOWN;

/**
 * All rights reserved & copyright ©
 */
public class LabelContainerSizeController {

    private final Input input;

    public LabelContainerSizeController(Input input) {
        this.input = input;
    }

    void adjustSize(short sizeVersion) {
        adjustSize(sizeVersion, ()->{});
    }

    void adjustSize(short sizeVersion, Runnable callback) {
        if(sizeVersion==SMALL || sizeVersion==UNKNOWN) {
            getContainer().setAlignment(Pos.BOTTOM_CENTER);

            Font labelFont = getTimeLabel().getFont();
            getContainer().minWidthProperty().bind(getMainContainer().widthProperty());
            getContainer().minHeightProperty().bind(getMainContainer().heightProperty().add(getBottomBlankSpace(labelFont)));
        } else {
            getContainer().setAlignment(Pos.CENTER);

            getContainer().minWidthProperty().bind(getMainContainer().widthProperty());
            getContainer().minHeightProperty().bind(getMainContainer().heightProperty());
        }
        callback.run();
    }

    private DoubleBinding getBottomBlankSpace(Font font) {
        return new DoubleBinding() {
            @Override
            protected double computeValue() {
                return Toolkit.getToolkit().getFontLoader().getFontMetrics(font).getMaxDescent()+ FontUtils.numberHeight(font)*0.08866995;
            }
        };
    }

    private VBox getContainer() {
        return input.getTimeLabelContainer();
    }

    private Region getMainContainer() {
        return input.getMainContainer();
    }

    private Label getTimeLabel() {
        return input.getTimeLabel();
    }

    interface Input {
        VBox getTimeLabelContainer();
        Region getMainContainer();
        Label getTimeLabel();
    }
}
