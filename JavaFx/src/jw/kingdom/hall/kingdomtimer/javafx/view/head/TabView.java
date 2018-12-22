package jw.kingdom.hall.kingdomtimer.javafx.view.head;

/**
 * All rights reserved & copyright ©
 */
enum TabView {
    TIME("time", "/layout/window/head/tab/time.fxml"),
    RECORDING("record", "/layout/window/head/tab/record.fxml"),
    SPEAKER("speaker", "/layout/window/head/tab/speaker.fxml"),
            ;

    public final String NAME;
    public final String PATH;

    TabView(String name, String path) {
        this.NAME = name;
        this.PATH = path;
    }
}
