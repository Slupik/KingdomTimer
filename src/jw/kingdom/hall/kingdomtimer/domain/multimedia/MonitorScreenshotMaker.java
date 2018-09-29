package jw.kingdom.hall.kingdomtimer.domain.multimedia;

import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * All rights reserved & copyright ©
 */
public interface MonitorScreenshotMaker {

    void setResolution(int x, int y);
    void setArea(int x, int y, int width, int height);
    void setArea(Rectangle rectangle);

    Image getFxImage();
    BufferedImage getBufferedImage();
}
