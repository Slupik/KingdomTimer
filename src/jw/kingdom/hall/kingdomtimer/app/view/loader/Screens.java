package jw.kingdom.hall.kingdomtimer.app.view.loader;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public enum Screens {
    PANEL_CONTROLLING("controlling", ScreenPaths.PANEL_LAYOUTS +"controlling.fxml"),
    VIEWER("viewer", ScreenPaths.VIEWER_LAYOUTS +"viewer.fxml"),
    HANDY_PANEL("handy", ScreenPaths.HANDY_PANEL_LAYOUTS +"handy.fxml");

    public final String NAME;
    public final String PATH;

    Screens(String name, String path){
        NAME = name;
        PATH = path;
    }
}
