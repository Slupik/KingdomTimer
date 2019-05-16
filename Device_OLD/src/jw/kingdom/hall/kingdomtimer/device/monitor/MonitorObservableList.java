/*
 * Copyright (c) 2017. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package jw.kingdom.hall.kingdomtimer.device.monitor;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import jw.kingdom.hall.kingdomtimer.domain.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.domain.monitor.MonitorEventHandler;
import jw.kingdom.hall.kingdomtimer.domain.monitor.MonitorListManager;

public class MonitorObservableList extends ObservableListWrapper<Monitor> {
	private final MonitorListManager manager;
	private boolean ignoreChange;

	MonitorObservableList(MonitorListManager manager) {
		super(manager.getAll());
		this.manager = manager;
		initialize();
	}

	private void initialize() {
		manager.addListener(new MonitorEventHandler() {
			@Override
			public void onPlugIn(Monitor monitor) {
                Platform.runLater(()->add(monitor));
			}

			@Override
			public void onPlugOut(Monitor monitor) {
                Platform.runLater(()->remove(monitor.getId()));
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
		Monitor toRemove = null;
		for(Monitor monitor:this){
			if(monitor.getId().equals(ID)){
				toRemove = monitor;
			}
		}
		if(toRemove!=null) {
			remove(toRemove);
			isRemoved=true;
		}
		return isRemoved;
	}

	public Monitor get(String ID) throws MonitorNotFound {
		for (Monitor monitor : this) {
			if (monitor.getId().equals(ID)) {
				return monitor;
			}
		}
		throw new MonitorNotFound();
	}

	public void set(Monitor newMonitor){
		for(int i=0;i<size();i++){
			Monitor monitor = get(i);
			if(monitor.getId().equals(newMonitor.getId())){
				set(i, newMonitor);
			}
		}
	}

	private void sortList() {
		ignoreChange = true;
		/*
		 * @return the value {@code 0} if {@code x == y};
		 *         a value less than {@code 0} if {@code x < y}; and
		 *         a value greater than {@code 0} if {@code x > y}
		 */
		sorted((o1, o2) -> {
			if(o1==null && o2!=null) {
				return 1;
			} else if(o1!=null && o2==null) {
				return -1;
			} else if(o1 == null) {
				return 0;
			}
			return Double.compare(o1.getX(), o2.getX())*(-1);
		});
		for(int i=0;i<size();i++){
			Monitor monitor = get(i);
			monitor.setPlace(i);
		}
		ignoreChange = false;
	}
}
