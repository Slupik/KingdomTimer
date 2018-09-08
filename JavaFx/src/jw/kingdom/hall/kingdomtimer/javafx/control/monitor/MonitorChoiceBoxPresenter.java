package jw.kingdom.hall.kingdomtimer.javafx.control.monitor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import jw.kingdom.hall.kingdomtimer.entity.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.entity.monitor.MonitorList;
import jw.kingdom.hall.kingdomtimer.javafx.utils.PlatformUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * All rights reserved & copyright ©
 */
public class MonitorChoiceBoxPresenter {

    private final MonitorList mList;
    private final ChoiceBox<Monitor> box;
    private final ObservableList<Monitor> oList = FXCollections.observableArrayList();
    private final List<Listener> listeners = new ArrayList<>();

    public MonitorChoiceBoxPresenter(MonitorList list, ChoiceBox<Monitor> box) {
        this.mList = list;
        this.box = box;
        this.box.setItems(oList);
        init();
    }

    private void init() {
        oList.addAll(mList.getMonitors());

        mList.addListener(() -> PlatformUtils.runOnUiThread(()->{
            Monitor lastValue = box.getValue();
            oList.setAll(mList.getMonitors());
            box.setValue(lastValue);
            if(!Objects.equals(lastValue, box.getValue())) {
                notifyAboutValueChange(lastValue);
            }
        }));

        box.setConverter(new StringConverter<Monitor>() {
            @Override
            public String toString(Monitor object) {
                return getDisplayName(object);
            }

            @Override
            public Monitor fromString(String string) {
                for(Monitor monitor:box.getItems()) {
                    if(getDisplayName(monitor).equals(string)) {
                        return monitor;
                    }
                }
                return null;
            }
        });

        box.valueProperty().addListener((observable, oldValue, newValue) -> notifyAboutValueChange(oldValue));
    }

    private String getDisplayName(Monitor object) {
        return "Wyświetlacz "+object.getPlace();
    }

    private Monitor lastNotified = null;
    private void notifyAboutValueChange(Monitor lastValue) {
        if(lastNotified != null && Objects.equals(lastNotified.getID(), box.getValue().getID())) {
            return;
        }
        for(Listener listener:listeners) {
            listener.onValueChange(lastValue, box.getValue());
        }
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    @FunctionalInterface
    public interface Listener {
        void onValueChange(Monitor last, Monitor now);
    }
}
