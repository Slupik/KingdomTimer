package jw.kingdom.hall.kingdomtimer.app.view.common;

import javafx.scene.layout.Region;
import jw.kingdom.hall.kingdomtimer.app.view.loader.ControlledScreen;
import jw.kingdom.hall.kingdomtimer.app.view.loader.ScreensController;

/**
 * All rights reserved & copyright ©
 */
public abstract class ControlledScreenImpl implements ControlledScreen {

    protected ScreensController myController;

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController=screenPage;

        setupAutoMaxSize();
    }

    private void setupAutoMaxSize() {
        if(getMainContainer()==null) {
            return;
        }
        getMainContainer().minHeightProperty().bind(
                myController.getStage().heightProperty()
        );
        getMainContainer().maxHeightProperty().bind(getMainContainer().minHeightProperty());
        getMainContainer().prefHeightProperty().bind(getMainContainer().minHeightProperty());

        getMainContainer().minWidthProperty().bind(
                myController.getStage().widthProperty()
        );
        getMainContainer().maxWidthProperty().bind(getMainContainer().minWidthProperty());
        getMainContainer().prefWidthProperty().bind(getMainContainer().minWidthProperty());
    }

    protected abstract Region getMainContainer();
}
