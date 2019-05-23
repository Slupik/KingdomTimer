/*
 * Copyright (c) 2017. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package jw.kingdom.hall.kingdomtimer.domain.monitor;

public interface MonitorEventHandler {
	void onPlugIn(Monitor device);
	void onPlugOut(Monitor device);
}
