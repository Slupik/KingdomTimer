package jw.kingdom.hall.kingdomtimer.view.panel.tabs.timeControl;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;
import jw.kingdom.hall.kingdomtimer.view.common.ControlledScreenImpl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class TimeControlController extends ControlledScreenImpl implements Initializable {

    @FXML
    Button btnStart;

    @FXML
    Button btnPause;

    @FXML
    Button btnStop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadImage(btnStart, "icons/baseline_play_arrow_black_48dp.png");
        loadImage(btnPause, "icons/baseline_pause_black_48dp.png");
        loadImage(btnStop, "icons/baseline_stop_black_48dp.png");
    }

    private void loadImage(Button button, String imgPath) {
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

    @Override
    protected Region getMainContainer() {
        return null;
    }
}
