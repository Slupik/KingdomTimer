/*
 * Copyright (c) 2017. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package jw.kingdom.hall.kingdomtimer.app.view.loader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;

public class ScreenUtils {
	public Node getScreenAsNode(String path) throws IOException {
		FXMLLoader myLoader= new FXMLLoader(getClass().getResource(path));
		return myLoader.<Parent>load();
	}
}
