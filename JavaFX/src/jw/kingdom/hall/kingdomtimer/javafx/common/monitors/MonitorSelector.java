package jw.kingdom.hall.kingdomtimer.javafx.common.monitors;

import javafx.scene.layout.AnchorPane;
import jw.kingdom.hall.kingdomtimer.domain.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.domain.monitor.MonitorEventHandler;
import jw.kingdom.hall.kingdomtimer.domain.monitor.MonitorListManager;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class MonitorSelector extends AnchorPane {

    private final List<MonitorSelectorListener> mListeners = new ArrayList<>();
    private final List<MonitorRepresentation> mRepresentations = new ArrayList<>();

    private String selected;

    public MonitorSelector(MonitorListManager monitorListManager) {
        monitorListManager.addListener(new MonitorEventHandler() {
            @Override
            public void onPlugIn(Monitor device) {
                reloadList(monitorListManager.getAll());
            }

            @Override
            public void onPlugOut(Monitor device) {
                reloadList(monitorListManager.getAll());
            }
        });
        reloadList(monitorListManager.getAll());
    }

    private void reloadList(List<Monitor> monitors) {
        getChildren().removeAll(getChildren());
        mRepresentations.clear();

        for(Monitor monitor:monitors) {
            MonitorRepresentation rect = new MonitorRepresentation(monitor);
            rect.setOnMouseClicked(event -> {
                if(!monitor.getId().equals(selected)){
                    setSelection(monitor.getId());
                    notifySelectNew(monitor);
                }
            });

            getChildren().add(rect);
            rect.setLayoutX(monitor.getX()/10);
            rect.setLayoutY(monitor.getY()/10);

            mRepresentations.add(rect);
        }
    }

    public void setSelection(String monitorId) {
        for(MonitorRepresentation representation:mRepresentations) {
            if(representation.getMonitor().getId().equals(monitorId)) {
                representation.select(true);
                representation.toFront();
                selected = monitorId;
            } else {
                representation.select(false);
            }
        }
    }

    public void addListener(MonitorSelectorListener listener) {
        mListeners.add(listener);
    }

    public void removeListener(MonitorSelectorListener listener) {
        mListeners.remove(listener);
    }

    private void notifySelectNew(Monitor monitor) {
        for(MonitorSelectorListener listener:mListeners) {
            listener.onSelectNewMonitor(monitor);
        }
    }
}
