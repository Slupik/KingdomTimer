package jw.kingdom.hall.kingdomtimer.domain.multimedia;

import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

/**
 * All rights reserved & copyright ©
 */
public interface MonitorViewDisplay {
    void display(BufferedImage image);
    void display(Image image);

    void setShowing(boolean showing);
    boolean isShowing();
    void forceShowing(boolean isShowing);

    void addListener(Listener listener);
    void removeListener(Listener listener);

    interface Listener {
        void onShowingChange(boolean isShowing);
    }
}
