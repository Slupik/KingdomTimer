package jw.kingdom.hall.kingdomtimer.monitor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class GraphicsDeviceListImpl implements GraphicsDeviceList {
    private final List<Listener> listeners = new ArrayList<>();
    private final List<GraphicsDevice> devices = new ArrayList<>();

    public GraphicsDeviceListImpl() {
        this(false);
    }

    public GraphicsDeviceListImpl(boolean activeSearch) {
        this(getSearchInterval(activeSearch));
    }

    private static int getSearchInterval(boolean activeSearch) {
        return activeSearch? 60:-1;
    }

    public GraphicsDeviceListImpl(int searchInterval) {
        if(searchInterval>=0) {
            new DevicesSearchingThread(searchInterval * 1000, new DevicesSearchingThread.Callback() {
                @Override
                public List<GraphicsDevice> getList() {
                    return devices;
                }

                @Override
                public void onPlugInDevice(GraphicsDevice gp) {
                    addDevice(gp);
                }

                @Override
                public void onPlugOutDevice(GraphicsDevice gp) {
                    removeDevice(gp);
                }
            }).start();
        }
    }

    private void addDevice(GraphicsDevice gp) {
        devices.add(gp);
        notifyOnPlugIn(gp);
    }

    private void removeDevice(GraphicsDevice gp) {
        List<GraphicsDevice> toDelete = new ArrayList<>();
        for(GraphicsDevice device:devices) {
            if(device.getIDstring().equals(gp.getIDstring())) {
                toDelete.add(device);
            }
        }
        devices.removeAll(toDelete);
        for(GraphicsDevice device:toDelete) {
            notifyOnPlugOut(device);
        }
    }

    private void notifyOnPlugIn(GraphicsDevice device) {
        for(Listener listener:listeners) {
            listener.onPlugIn(device);
        }
    }

    private void notifyOnPlugOut(GraphicsDevice device) {
        for(Listener listener:listeners) {
            listener.onPlugOut(device);
        }
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
    public List<GraphicsDevice> getDevices() {
        return devices;
    }
}
