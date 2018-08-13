/*
 * Copyright (c) 2017. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package jw.kingdom.hall.kingdomtimer.device.monitor;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ListChangeListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MonitorObservableList extends ObservableListWrapper<Monitor> {
	private boolean ignoreChange;

	MonitorObservableList(){
		this(new ArrayList<>());
	}

	private MonitorObservableList(List<Monitor> list) {
		super(list);
		initialize();
	}

	private void initialize() {
		MonitorManager.addListener(new MonitorEventHandler() {
			@Override
			public void onPlugIn(GraphicsDevice device) {
				Monitor monitor = new Monitor(device);
				add(monitor);
			}

			@Override
			public void onPlugOut(GraphicsDevice device) {
				Monitor monitor = new Monitor(device);
				remove(monitor.ID);
			}
		});

		addListener((ListChangeListener<Monitor>) change -> {
			if(ignoreChange){
				return;
			}
			sortList();
		});
	}

	public boolean remove(String ID) {
		boolean isRemoved = false;
		for(Monitor monitor:this){
			if(monitor.ID.equals(ID)){
				remove(monitor);
				isRemoved=true;
			}
		}
		return isRemoved;
	}

	public Monitor get(String ID) throws MonitorNotFound {
		for (Monitor monitor : this) {
			if (monitor.ID.equals(ID)) {
				return monitor;
			}
		}
		throw new MonitorNotFound();
	}

	public void set(Monitor newMonitor){
		for(int i=0;i<size();i++){
			Monitor monitor = get(i);
			if(monitor.ID.equals(newMonitor.ID)){
				set(i, newMonitor);
			}
		}
	}

	private void sortList() {
		ignoreChange = true;
		sort((screen1, screen2) -> {
			Rectangle bounds1 = screen1.getBounds();
			Rectangle bounds2 = screen2.getBounds();
			int c = bounds1.y - bounds2.y;
			if (c == 0) {
				c = bounds1.x - bounds2.x;
			}
			return c;
		}
		);
		for(int i=0;i<size();i++){
			Monitor monitor = get(i);
			monitor.setPlace(i);
		}
		ignoreChange = false;
	}
}
