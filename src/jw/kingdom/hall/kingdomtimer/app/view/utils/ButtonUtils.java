package jw.kingdom.hall.kingdomtimer.app.view.utils;

import com.google.common.io.Resources;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * All rights reserved & copyright ©
 */
public class ButtonUtils {

    public static void loadSmallImage(Button button, String imgPath) {
        loadImage(button, imgPath, 15);
    }

    public static void loadMediumImage(Button button, String imgPath) {
        loadImage(button, imgPath, 30);
    }

    public static void loadBigImage(Button button, String imgPath) {
        loadImage(button, imgPath, 45);
    }

    public static void loadImage(Button button, String imgPath, int size) {
        URL url = Resources.getResource(imgPath);
        try {
            BufferedImage bufferedImage = ImageIO.read(url);
            Image graphic = SwingFXUtils.toFXImage(bufferedImage, null);
            ImageView image = new ImageView(graphic);
            image.setPreserveRatio(true);
            image.setFitHeight(size);
            button.setGraphic(image);
            button.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
