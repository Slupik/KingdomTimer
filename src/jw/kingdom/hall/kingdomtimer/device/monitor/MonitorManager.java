/*
 * Copyright (c) 2017. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package jw.kingdom.hall.kingdomtimer.device.monitor;

import java.awt.*;
import java.util.ArrayList;

public class MonitorManager {
	private static boolean wasInitialized = false;
	private static ArrayList<MonitorEventHandler> listeners = new ArrayList<>();
	static GraphicsDevice[] lastDevicesList = new GraphicsDevice[0];

	public static final MonitorObservableList monitors = new MonitorObservableList();

	public static boolean initialize(){
		if(wasInitialized){
			return false;
		}
		wasInitialized=true;
		runThread();
		return true;
	}

	private static void runThread() {
		Thread searchingThread = new SearchingThread();
		searchingThread.start();
	}

	static boolean isOldListContains(GraphicsDevice gd){
		return listContains(lastDevicesList, gd);
	}

	static boolean listContains(GraphicsDevice[] list, GraphicsDevice gd){
		for(GraphicsDevice checkingGD:list){
			if(checkingGD.getIDstring().equals(gd.getIDstring())){
				return true;
			}
		}
		return false;
	}

	static void firePlugInEvent(GraphicsDevice device){
		for(MonitorEventHandler handler:listeners){
			handler.onPlugIn(device);
		}
	}
	static void firePlugOutEvent(GraphicsDevice device){
		for(MonitorEventHandler handler:listeners){
			handler.onPlugOut(device);
		}
	}

	public static void addListener(MonitorEventHandler handler){
		listeners.add(handler);
	}
	public static void removeListener(MonitorEventHandler handler){
		listeners.remove(handler);
	}

	public static Monitor[] getMonitorsArray() {
		return monitors.toArray(new Monitor[0]);
	}
}
