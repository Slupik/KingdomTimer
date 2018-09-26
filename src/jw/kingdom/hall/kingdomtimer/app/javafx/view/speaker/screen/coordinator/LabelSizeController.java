package jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker.screen.coordinator;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import jw.kingdom.hall.kingdomtimer.app.javafx.utils.FontUtils;

import static jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker.screen.coordinator.LabelVersion.SMALL;
import static jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker.screen.coordinator.LabelVersion.UNKNOWN;

/**
 * All rights reserved & copyright ©
 */
class LabelSizeController {
    private static final double RATIO_LABEL_WIDTH_TO_CONTAINER = 0.9;
    private static final double RATIO_MARGIN_OF_LABEL_TO_CONTAINER = 0.1;

    private final Input input;

    private int lastSizeVersion = UNKNOWN;

    LabelSizeController(Input input) {
        this.input = input;
        init();
    }

    private void init() {
        getLabel().setMinWidth(Region.USE_COMPUTED_SIZE);
        getLabel().setMaxWidth(Region.USE_COMPUTED_SIZE);
    }

    void adjustSize(short sizeVersion, double freeHeight, double freeWidth, Runnable callback) {
        adjustFont(freeHeight, freeWidth);
        if(lastSizeVersion!=sizeVersion || sizeVersion!=LabelVersion.BIG) {
            lastSizeVersion = sizeVersion;
            adjustPadding(sizeVersion, freeWidth);
        }
        callback.run();
    }

    private void adjustPadding(short sizeVersion, double freeWidth) {
        Insets padding;
        if(sizeVersion==SMALL || sizeVersion==UNKNOWN) {
            padding = new Insets(0,(int)(freeWidth*RATIO_MARGIN_OF_LABEL_TO_CONTAINER),0,0);
        } else {
            padding = new Insets(0, 0,0,0);
        }
        getLabel().setPadding(padding);
    }

    private void adjustFont(double freeHeight, double freeWidth) {
        double maxWidth = freeWidth * RATIO_LABEL_WIDTH_TO_CONTAINER;
        Font newFont = getNewFontForTextLabel(freeHeight, maxWidth);
        getLabel().setFont(newFont);
    }

    private Font getNewFontForTextLabel(double maxHeight, double maxWidth) {
        Font font = getLabel().getFont();

        Font finalFont = getFontForHeight(font, maxHeight);
        if(isLabelTooWide(finalFont, maxWidth)) {
            return getFontForWidth(finalFont, maxWidth);
        } else {
            return finalFont;
        }
    }

    private boolean isLabelTooWide(Font newFont, double maxWidth) {
        double newWidth = FontUtils.textWidth(newFont, getLabel().getText());
        return newWidth>maxWidth;
    }

    private Font getFontForHeight(Font source, double maxHeight) {
        double newSize = FontUtils.findFontSizeForNumberHeight(source, (int) maxHeight);
        return new Font(source.getName(), newSize);
    }

    private Font getFontForWidth(Font source, double maxWidth) {
        double newSize = FontUtils.findFontSizeForWidth(source, getLabel().getText(), (int) (maxWidth));
        return new Font(source.getName(), newSize);
    }

    private Label getLabel() {
        return input.getTimeLabel();
    }

    interface Input {
        Label getTimeLabel();
    }
}
