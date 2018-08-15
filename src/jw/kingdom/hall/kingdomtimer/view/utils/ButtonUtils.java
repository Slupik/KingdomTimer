package jw.kingdom.hall.kingdomtimer.view.utils;

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

    public static void loadImage(Button button, String imgPath) {
        URL url = Resources.getResource(imgPath);
        try {
            BufferedImage bufferedImage = ImageIO.read(url);
            Image graphic = SwingFXUtils.toFXImage(bufferedImage, null);
            ImageView image = new ImageView(graphic);
            image.setPreserveRatio(true);
            image.setFitHeight(30);
            button.setGraphic(image);
            button.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
