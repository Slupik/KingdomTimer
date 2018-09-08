package jw.kingdom.hall.kingdomtimer.monitor;


import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 * All rights reserved & copyright ©
 */
class DevicesSearchingThread extends Thread {
    private int interval;
    private final Callback callback;

    DevicesSearchingThread(int millis, Callback callback) {
        this.interval = millis;
        this.callback = callback;
    }

    @Override
    public void run() {
        super.run();
        while (interval>=0) {
            GraphicsDevice[] actDeviceList = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
            List<GraphicsDevice> lastList = callback.getList();
            for(GraphicsDevice gp:actDeviceList){
                if(!isListContains(lastList, gp)){
                    callback.onPlugInDevice(gp);
                }
            }
            for(GraphicsDevice gp:lastList){
                if(!isListContains(actDeviceList, gp)){
                    callback.onPlugOutDevice(gp);
                }
            }
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isListContains(List<GraphicsDevice> lastList, GraphicsDevice gp) {
        return isListContains(lastList.toArray(new GraphicsDevice[0]), gp);
    }

    private boolean isListContains(GraphicsDevice[] lastList, GraphicsDevice gp) {
        for(GraphicsDevice device:lastList) {
            if(Objects.equals(device.getIDstring(), gp.getIDstring())) {
                return true;
            }
        }
        return false;
    }

    void tryToStop(){
        interval=-1;
    }

    interface Callback {
        List<GraphicsDevice> getList();
        void onPlugInDevice(GraphicsDevice gp);
        void onPlugOutDevice(GraphicsDevice gp);
    }
}
