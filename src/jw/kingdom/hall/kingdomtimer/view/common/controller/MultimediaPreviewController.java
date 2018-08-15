package jw.kingdom.hall.kingdomtimer.view.common.controller;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import jw.kingdom.hall.kingdomtimer.view.utils.Randomizer;

/**
 * All rights reserved & copyright ©
 */
public class MultimediaPreviewController {
    private String id = Randomizer.randomStandardString(10);
    private boolean disableHiding = false;

    private ImageView view;

    public MultimediaPreviewController(ImageView view) {
        this.view = view;
    }

    public void setImage(Image image){
        if(Platform.isFxApplicationThread()) {
            this.view.setImage(image);
        } else {
            Platform.runLater(()->this.view.setImage(image));
        }
    }

    public String getId(){
        return id;
    }

    public void showPreview(boolean value) {
        if(!disableHiding) {
            view.setVisible(value);
        }
    }

    public boolean isShowing() {
        return view.isVisible();
    }

    public void setDisableHiding(boolean disableHiding) {
        this.disableHiding = disableHiding;
    }
}
