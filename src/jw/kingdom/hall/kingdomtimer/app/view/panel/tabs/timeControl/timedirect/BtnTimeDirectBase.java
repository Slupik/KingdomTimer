package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl.timedirect;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import jw.kingdom.hall.kingdomtimer.domain.utils.Randomizer;

import java.util.ArrayList;
import java.util.List;

import static jw.kingdom.hall.kingdomtimer.app.view.utils.ButtonUtils.loadMediumImage;
import static jw.kingdom.hall.kingdomtimer.app.view.utils.ButtonUtils.loadSmallImage;

/**
 * All rights reserved & copyright ©
 */
public abstract class BtnTimeDirectBase {
    private final List<Listener> listeners = new ArrayList<>();
    private Button button;
    private boolean isMedium = true;

    protected BtnTimeDirectBase(Button button) {
        this.button = button;
    }

    protected void init() {
        updateImage();
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                changeDirect();
            }
        });
    }

    protected abstract void changeDirect();
    protected abstract boolean isDirectDown();

    protected void updateImage() {
        setImageForArrowDown(isDirectDown());
    }

    private void setImageForArrowDown(boolean isDirectDown) {
        if(isDirectDown) {
            if(isMedium) {
                loadMediumImage(button, "icons/baseline_arrow_drop_down_black_48dp.png");
            } else {
                loadSmallImage(button, "icons/baseline_arrow_drop_down_black_48dp.png");
            }
        } else {
            if(isMedium) {
                loadMediumImage(button, "icons/baseline_arrow_drop_up_black_48dp.png");
            } else {
                loadSmallImage(button, "icons/baseline_arrow_drop_up_black_48dp.png");
            }
        }
    }

    public void setMedium(boolean medium) {
        isMedium = medium;
        updateImage();
    }

    protected void notifyCountdownDirectChange(boolean isDirectDown){
        for(Listener listener:listeners) {
            listener.onDirectChange(isDirectDown);
        }
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public interface Listener {
        String getId();
        void onDirectChange(boolean isDirectDown);
    }

    public abstract static class ListenerImpl implements Listener {
        private final String ID = Randomizer.randomStandardString(10);

        @Override
        public String getId() {
            return ID;
        }

        public abstract void onDirectChange(boolean isDirectDown);
    }
}
