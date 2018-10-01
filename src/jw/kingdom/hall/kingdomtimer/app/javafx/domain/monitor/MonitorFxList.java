package jw.kingdom.hall.kingdomtimer.app.javafx.domain.monitor;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.application.Platform;
import jw.kingdom.hall.kingdomtimer.domain.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.domain.monitor.MonitorEventHandler;
import jw.kingdom.hall.kingdomtimer.domain.monitor.MonitorListManager;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class MonitorFxList extends ObservableListWrapper<Monitor> {

    private final MonitorListManager manager;

    public MonitorFxList(MonitorListManager manager) {
        super(manager.getAll());
        this.manager = manager;
        init();
    }

    private void init() {
        manager.addListener(new MonitorEventHandler() {
            @Override
            public void onPlugIn(Monitor device) {
                Platform.runLater(()-> add(device));
            }

            @Override
            public void onPlugOut(Monitor device) {
                Platform.runLater(()->{
                    List<Monitor> toDelete = new ArrayList<>();
                    for(Monitor monitor:MonitorFxList.this){
                        if(monitor.getId().equals(device.getId())) {
                            toDelete.add(monitor);
                        }
                    }
                    removeAll(toDelete);
                });
            }
        });
    }
}
