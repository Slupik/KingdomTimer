/*
 * Copyright (c) 2017. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package jw.kingdom.hall.kingdomtimer.device.monitor;

import java.awt.*;

public class Monitor extends GraphicsDevice {
	private boolean isMain = false;
	private int place = 0;
	private final GraphicsDevice ORIGINAL_DEVICE;
	public final String ID;

	public Monitor(GraphicsDevice gp){
		ORIGINAL_DEVICE = gp;
		ID = gp.getIDstring();

		setAutoMain();
	}

	private void setAutoMain() {
		Rectangle bounds = getBounds();
		if(bounds.getX()==0 && bounds.getY() == 0){
			setMain(true);
		}
	}

	public boolean isMain() {
		return isMain;
	}

	public void setMain(boolean main) {
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

	public int getPlace() {
		return place;
	}

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
