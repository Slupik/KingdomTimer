package jw.kingdom.hall.kingdomtimer.usecase.monitor;

import jw.kingdom.hall.kingdomtimer.entity.monitor.Monitor;

import java.awt.*;

/**
 * All rights reserved & copyright ©
 */
public class MonitorBean implements Monitor {
    private final GraphicsDevice ORIGINAL_DEVICE;
    private boolean isPrimary = false;
    private int place = -1;

    public MonitorBean(GraphicsDevice gp){
        ORIGINAL_DEVICE = gp;
        setAutoMain();
        setAutoPlace();
    }

    private void setAutoPlace() {
        String name = ORIGINAL_DEVICE.getIDstring();
        String digits = name.replaceAll("\\D+","");
        int value = -100;
        if(digits.length()>0) {
            value = Integer.parseInt(digits);
        }
        setPlace(value);
    }

    private void setAutoMain() {
        Rectangle bounds = getBounds();
        if(bounds.getX()==0 && bounds.getY() == 0){
            setPrimary(true);
        }
    }

    @Override
    public boolean isPrimary() {
        return isPrimary;
    }

    @Override
    public void setPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    @Override
    public int getPlace() {
        return place;
    }

    @Override
    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    public Rectangle getBounds() {
        return ORIGINAL_DEVICE.getDefaultConfiguration().getBounds();
    }

    @Override
    public Type getType() {
        return Monitor.Type.getById(ORIGINAL_DEVICE.getType());
    }

    @Override
    public String getID() {
        return ORIGINAL_DEVICE.getIDstring();
    }

}
