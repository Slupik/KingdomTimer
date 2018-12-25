package jw.kingdom.hall.kingdomtimer.device.screen;

import com.google.common.io.Resources;
import javafx.embed.swing.SwingFXUtils;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MonitorScreenshotMaker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class ScreenShotMaker implements MonitorScreenshotMaker {
    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static final String CURSOR_IMG_PATH = "images/cursor.png";
    private Rectangle resolution = new Rectangle(-1, -1);
    private Rectangle area = new Rectangle(-1, -1);

    @Override
    public void setResolution(int x, int y) {
        resolution = new Rectangle(x, y);
    }

    @Override
    public void setArea(int x, int y, int width, int height) {
        setArea(new Rectangle(x, y, width, height));
    }

    @Override
    public void setArea(Rectangle rectangle) {
        area = rectangle;
    }

    @Override
    public javafx.scene.image.Image getFxImage() {
        return SwingFXUtils.toFXImage(getBufferedImage(), null);
    }

    @Override
    public BufferedImage getBufferedImage() {
        try {
            return getSS(area);
        } catch (IOException e) {
            e.printStackTrace();
            return new BufferedImage(area.width, area.height, TYPE_INT_ARGB);
        }
    }

    public static BufferedImage getTestSS() throws IOException {
        return getSS(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
    }

    public static BufferedImage getSS(Rectangle size) throws IOException {
        BufferedImage capture = robot.createScreenCapture(size);

        Point mousePos = new Point(0, 0);
        try {
            mousePos = MouseInfo.getPointerInfo().getLocation();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        if(isMouseOnTheArea(mousePos, size)) {
            Point fixedMousePos = getFixedMousePos(mousePos, size);
            int x = fixedMousePos.x;
            int y = fixedMousePos.y;

            URL url = Resources.getResource(CURSOR_IMG_PATH);
            Image cursor = ImageIO.read(url);

            Graphics2D graphics2D = capture.createGraphics();
            graphics2D.drawImage(cursor, x, y, 16, 16, null); // cursor.gif is 16x16 size.
        }
        return capture;
    }

    private static Point getFixedMousePos(Point mousePos, Rectangle size) {
        return new Point(mousePos.x-size.x,
                mousePos.y-size.y);
    }

    private static boolean isMouseOnTheArea(Point point, Rectangle size) {
        int x = point.x;
        int y = point.y;
        return (
                x>size.x && x<(size.x+size.width)
                        && y>size.y && y<(size.y+size.height)
        );
    }

//    private static Point getFixedMousePos(Point mousePos, Rectangle size, BufferedImage capture) {
//        double calibratedX = mousePos.x-size.x;
//        double calibratedY = mousePos.y-size.y;
//        double fixedX = calibratedX/size.width*capture.getWidth();
//        double fixedY = calibratedY/size.height*capture.getHeight();
//        return new Point(((int) fixedX), ((int) fixedY));
//    }
}
