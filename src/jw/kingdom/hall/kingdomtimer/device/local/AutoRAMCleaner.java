/*
 * Copyright (c) 2017. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package jw.kingdom.hall.kingdomtimer.device.local;

@SuppressWarnings("InfiniteLoopStatement")
public class AutoRAMCleaner {
	private static boolean isRunning = false;
	/*
	Consumes more than frees so should be OFF
	For newest version (up Beta 4.5.0) it can be useful
	 */
	private final static boolean BLOCK = false;

	public static void run(){
		if(isRunning || BLOCK){
			return;
		}
		isRunning = true;
		new Thread(()-> {
			while(true) {
				try {
					Thread.sleep(2*60 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Runtime.getRuntime().gc();
			}
		});
	}
}
