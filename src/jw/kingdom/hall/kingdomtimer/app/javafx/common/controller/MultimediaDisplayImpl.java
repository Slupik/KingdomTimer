package jw.kingdom.hall.kingdomtimer.app.javafx.common.controller;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MonitorViewDisplay;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class MultimediaDisplayImpl implements MonitorViewDisplay {
    private final List<MonitorViewDisplay.Listener> listeners = new ArrayList<>();
    private boolean lastShowing;
    private final ImageView view;

    public MultimediaDisplayImpl(ImageView view) {
        this.view = view;
        lastShowing = view.isVisible();
    }

    @Override
    public void display(BufferedImage image) {
        display(SwingFXUtils.toFXImage(image, null));
    }

    @Override
    public void display(Image image) {
        if(Platform.isFxApplicationThread()) {
            this.view.setImage(image);
        } else {
            Platform.runLater(()->this.view.setImage(image));
        }
    }

    @Override
    public void setShowing(boolean showing) {
        if(lastShowing!=showing) {
            lastShowing = showing;
            view.setVisible(showing);
            for(Listener listener:listeners) {
                listener.onShowingChange(showing);
            }
        }
    }

    @Override
    public boolean isShowing() {
        return view.isVisible();
    }

    @Override
    public void forceShowing(boolean isShowing) {
        setShowing(isShowing);
    }

    @Override
    public void addListener(MonitorViewDisplay.Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(MonitorViewDisplay.Listener listener) {
        listeners.remove(listener);
    }
}
