/*
 * Copyright (c) 2017. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package jw.kingdom.hall.kingdomtimer.domain.monitor;

public interface Monitor {
	String getId();

	double getX();
	double getY();

	double getWidth();
	double getHeight();

    boolean isPrimary();
    void setPrimary(boolean main);

    int getPlace();
    void setPlace(int place);
}
