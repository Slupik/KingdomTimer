/*
 * Copyright (c) 2017. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package jw.kingdom.hall.kingdomtimer.device.monitor;

import jw.kingdom.hall.kingdomtimer.domain.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.domain.monitor.MonitorEventHandler;
import jw.kingdom.hall.kingdomtimer.domain.monitor.MonitorListManager;

import java.util.ArrayList;
import java.util.List;

public class MonitorListManagerImpl implements MonitorListManager {
	private static MonitorListManager instance;

	private ArrayList<MonitorEventHandler> listeners = new ArrayList<>();

	private MonitorObservableList monitors;

	public static MonitorListManager getInstance() {
		if (instance==null) {
            instance = new MonitorListManagerImpl();
		}
		return instance;
	}

    private MonitorListManagerImpl() {
	    monitors = new MonitorObservableList(this);
        runThread();
    }

	private void runThread() {
		Thread searchingThread = new SearchingThread(this);
		searchingThread.start();
	}

	void firePlugInEvent(Monitor monitor) {
		for (MonitorEventHandler handler : listeners) {
			handler.onPlugIn(monitor);
		}
	}

	void firePlugOutEvent(Monitor monitor) {
		for (MonitorEventHandler handler : listeners) {
			handler.onPlugOut(monitor);
		}
	}

	@Override
	public void addListener(MonitorEventHandler handler) {
		listeners.add(handler);
	}

	@Override
	public void removeListener(MonitorEventHandler handler) {
		listeners.remove(handler);
	}

	@Override
	public List<Monitor> getAll() {
	    if(monitors==null) {
	        return new ArrayList<>();
        }
		return monitors;
	}
}