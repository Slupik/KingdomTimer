/*
 * Copyright (c) 2017. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package jw.kingdom.hall.kingdomtimer.device.monitor;

import java.awt.*;
import java.util.Calendar;

class SearchingThread extends Thread {
	private Thread t;
	private String threadName;
	private static final int START_WAIT_TIME = 100;
	private static final int SEARCHING_INTERVAL = 5*1000;
	private static boolean wasInitialized = false;


	SearchingThread() {
		threadName = "SearchingThread";
	}

	public void run() {
		if(wasInitialized){
			return;
		}
		wasInitialized=true;
		try {
			Thread.sleep(START_WAIT_TIME);
			while(true){
				try {
					GraphicsDevice[] actDeviceList = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
					for(GraphicsDevice gp:actDeviceList){
						if(!MonitorManager.isOldListContains(gp)){
							MonitorManager.firePlugInEvent(gp);
						}
					}
					for(GraphicsDevice gp:MonitorManager.lastDevicesList){
						if(!MonitorManager.listContains(actDeviceList, gp)){
							MonitorManager.firePlugOutEvent(gp);
						}
					}
					MonitorManager.lastDevicesList = actDeviceList;
				} catch (Exception e) {
					e.printStackTrace();
				}
				Thread.sleep(SEARCHING_INTERVAL);
			}
		}catch (InterruptedException e) {
			System.out.println("Thread " +  threadName + " interrupted.");
		}
	}

	public void start () {
		if(wasInitialized){
			return;
		}
		if (t == null) {
			t = new Thread (this, threadName);
			t.start ();
		}
	}
}
