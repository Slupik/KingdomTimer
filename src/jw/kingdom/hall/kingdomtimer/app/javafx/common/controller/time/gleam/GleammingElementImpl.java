package jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.time.gleam;

import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.time.label.TimeLabel;

/**
 * All rights reserved & copyright ©
 */
public class GleammingElementImpl implements GleammingElement {

    private final Region container;
    private final TimeLabel text;

    private boolean gleammingMode = false;
    private Background defaultBackground;

    public GleammingElementImpl(Region background, TimeLabel text) {
        this.container = background;
        this.text = text;
        defaultBackground = container.getBackground();
    }

    @Override
    public void startGleamming() {
        enterGleammingMode();
    }

    @Override
    public void applyColors(Background background, Paint textColor) {
        container.setBackground(background);
        text.setGleammingColor(textColor);
    }

    @Override
    public Background getDefaultBackground() {
        return defaultBackground;
    }

    @Override
    public void endGleamming() {
        leaveGleammingMode();
        container.setBackground(defaultBackground);
    }

    private void enterGleammingMode() {
        if(!gleammingMode) {
            gleammingMode = true;
            text.enterGleammingMode();
            defaultBackground = container.getBackground();
        }
    }

    private void leaveGleammingMode() {
        text.leaveGleammingMode();
        gleammingMode = false;
    }
}
