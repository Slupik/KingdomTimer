package jw.kingdom.hall.kingdomtimer.javafx.common.monitors;

import javafx.application.Platform;
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

    private Monitor mSelected;

    public MonitorSelector(MonitorListManager monitorListManager) {
        monitorListManager.addListener(new MonitorEventHandler() {
            @Override
            public void onPlugIn(Monitor device) {
                Platform.runLater(()-> reloadList(monitorListManager.getAll()));
            }

            @Override
            public void onPlugOut(Monitor device) {
                Platform.runLater(()-> reloadList(monitorListManager.getAll()));
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
                if(mSelected==null || !monitor.getId().equals(mSelected.getId())){
                    setSelection(monitor);
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
            if (representation.getMonitor().getId().equals(monitorId)) {
                setSelection(representation.getMonitor());
                return;
            }
        }
    }

    public void setSelection(Monitor monitor) {
        for(MonitorRepresentation representation:mRepresentations) {
            if(representation.getMonitor().getId().equals(monitor.getId())) {
                representation.select(true);
                representation.toFront();
            } else {
                representation.select(false);
            }
        }
        Monitor lastSelected = mSelected;
        mSelected = monitor;

        notifySelectNew(monitor, lastSelected);
    }

    public void addListener(MonitorSelectorListener listener) {
        mListeners.add(listener);
    }

    public void removeListener(MonitorSelectorListener listener) {
        mListeners.remove(listener);
    }

    private void notifySelectNew(Monitor newMonitor, Monitor oldMonitor) {
        for(MonitorSelectorListener listener:mListeners) {
            listener.onSelectNewMonitor(newMonitor, oldMonitor);
        }
    }

    public Monitor getSelected() {
        return mSelected;
    }
}
