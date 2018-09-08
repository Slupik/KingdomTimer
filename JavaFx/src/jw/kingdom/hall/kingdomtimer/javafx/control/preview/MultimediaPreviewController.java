package jw.kingdom.hall.kingdomtimer.javafx.control.preview;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.config.model.ConfigReadable;
import jw.kingdom.hall.kingdomtimer.entity.utils.Randomizer;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class MultimediaPreviewController {
    private String id = Randomizer.randomStandardString(10);
    private boolean disableHiding = false;
    private final List<Listener> listeners = new ArrayList<>();

    private final Config config;
    private ImageView view;

    public MultimediaPreviewController(ImageView view, Config config) {
        this.view = view;
        this.config = config;
        init();
    }

    private void init() {
        showPreview(getConfig().isEnabledShowMultimedia());
    }

    private ConfigReadable getConfig() {
        return config;
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
            for(Listener listener:listeners) {
                listener.onVisibilityChange();
            }
        }
    }

    public boolean isShowing() {
        return view.isVisible();
    }

    public void setDisableHiding(boolean disableHiding) {
        this.disableHiding = disableHiding;
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public interface Listener {
        void onVisibilityChange();
    }
}
