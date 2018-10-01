package jw.kingdom.hall.kingdomtimer.device.monitor;

import jw.kingdom.hall.kingdomtimer.domain.monitor.Monitor;

import java.awt.*;

/**
 * All rights reserved & copyright ©
 */
public class GraphicsMonitor extends GraphicsDevice implements Monitor {
    private boolean isMain = false;
    private int place = 0;
    private final GraphicsDevice ORIGINAL_DEVICE;
    public final String ID;

    public GraphicsMonitor(GraphicsDevice gp){
        ORIGINAL_DEVICE = gp;
        ID = gp.getIDstring();

        setAutoMain();
    }

    private void setAutoMain() {
        Rectangle bounds = getBounds();
        if(bounds.getX()==0 && bounds.getY() == 0){
            setPrimary(true);
        }
    }

    @Override
    public String getId() {
        return getIDstring();
    }

    @Override
    public double getX() {
        return getDefaultConfiguration().getBounds().getX();
    }

    @Override
    public double getY() {
        return getDefaultConfiguration().getBounds().getY();
    }

    @Override
    public double getWidth() {
//        return getDefaultConfiguration().getBounds().getWidth();
        return getDisplayMode().getWidth();
    }

    @Override
    public double getHeight() {
//        return getDefaultConfiguration().getBounds().getHeight();
        return getDisplayMode().getHeight();
    }

    @Override
    public boolean isPrimary() {
        return isMain;
    }

    @Override
    public void setPrimary(boolean main) {
        isMain = main;
    }

    Rectangle getBounds() {
        return ORIGINAL_DEVICE.getDefaultConfiguration().getBounds();
    }

    public void setBounds(int width, int height){
        setBounds(new Rectangle(width, height));
    }

    private void setBounds(Rectangle bounds) {
        ORIGINAL_DEVICE.setDisplayMode(
                new DisplayMode(
                        bounds.width,
                        bounds.height,
                        ORIGINAL_DEVICE.getDisplayMode().getBitDepth(),
                        ORIGINAL_DEVICE.getDisplayMode().getRefreshRate()
                )
        );
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
    public int getType() {
        return ORIGINAL_DEVICE.getType();
    }

    @Override
    public String getIDstring() {
        return ORIGINAL_DEVICE.getIDstring();
    }

    @Override
    public GraphicsConfiguration[] getConfigurations() {
        return ORIGINAL_DEVICE.getConfigurations();
    }

    @Override
    public GraphicsConfiguration getDefaultConfiguration() {
        return ORIGINAL_DEVICE.getDefaultConfiguration();
    }

    public String getName(){
        return "Wyświetlacz "+getPlace();
    }

    @Override
    public String toString(){
        if(isMain){
            return getName()+"(Główny)";
        } else {
            return getName();
        }
    }
}
