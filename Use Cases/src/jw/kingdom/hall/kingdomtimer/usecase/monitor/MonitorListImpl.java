package jw.kingdom.hall.kingdomtimer.usecase.monitor;

import jw.kingdom.hall.kingdomtimer.entity.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.entity.monitor.MonitorList;
import jw.kingdom.hall.kingdomtimer.monitor.GraphicsDeviceList;
import jw.kingdom.hall.kingdomtimer.monitor.GraphicsDeviceListImpl;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class MonitorListImpl implements MonitorList {
    private final List<Listener> listeners = new ArrayList<>();
    private final List<Monitor> monitors = new ArrayList<>();

    public MonitorListImpl() {
        this(new GraphicsDeviceListImpl(4));
    }

    public MonitorListImpl(GraphicsDeviceList source) {
        setAll(getAsMonitorList(source.getDevices()));
        source.addListener(new GraphicsDeviceList.Listener() {
            @Override
            public void onPlugIn(GraphicsDevice device) {
                addDevice(getAsMonitor(device));
            }

            @Override
            public void onPlugOut(GraphicsDevice device) {
                removeDevice(getAsMonitor(device));
            }
        });
    }

    private void setAll(List<Monitor> list) {
        monitors.clear();
        monitors.addAll(list);
        notifyOnChange();
    }

    private List<Monitor> getAsMonitorList(List<GraphicsDevice> devices) {
        List<Monitor> list = new ArrayList<>();
        for(GraphicsDevice device:devices) {
            list.add(getAsMonitor(device));
        }
        return list;
    }

    private void addDevice(Monitor device) {
        if(device.getType()!=Monitor.Type.RASTER_SCREEN) {
            return;
        }
        monitors.add(device);
        notifyOnChange();
    }

    private void removeDevice(Monitor device) {
        if(device.getType()!=Monitor.Type.RASTER_SCREEN) {
            return;
        }
        List<Monitor> toDelete = new ArrayList<>();
        for(Monitor monitor:monitors) {
            if(monitor.getID().equals(device.getID())) {
                toDelete.add(monitor);
            }
        }
        monitors.removeAll(toDelete);
        notifyOnChange();
    }

    private void notifyOnChange() {
        for(Listener listener:listeners) {
            listener.onChange();
        }
    }

    private Monitor getAsMonitor(GraphicsDevice gp) {
        return new MonitorBean(gp);
    }

    @Override
    public List<Monitor> getMonitors() {
        return monitors;
    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public Monitor findById(String speakerScreen) {
        for(Monitor monitor:monitors) {
            if(monitor.getID().equals(speakerScreen)) {
                return monitor;
            }
        }
        return null;
    }
}
