package jw.kingdom.hall.kingdomtimer.javafx.view.head;

/**
 * All rights reserved & copyright ©
 */
enum TabView {
    TIME("time", "/layout/window/head/tabs/time.fxml"),
    RECORDING("record", "/layout/window/head/tabs/record.fxml"),
    SPEAKER("speaker", "/layout/window/head/tabs/speaker.fxml"),
            ;

    public final String NAME;
    public final String PATH;

    TabView(String name, String path) {
        this.NAME = name;
        this.PATH = path;
    }
}
