package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl.table;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.TableRow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

/**
 * All rights reserved & copyright ©
 */
public class TaskTableRow extends TableRow<MeetingTask> {
    private MeetingTask lastTask;
    private Background defaultBackground = null;

    public TaskTableRow(){
        super();
        if(defaultBackground==null) {
            defaultBackground = getBackground();
        }
    }

    private ChangeListener<MeetingTask.Type> backgroundChanger = (observable, oldValue, newValue) -> {
        setRowColor(this, newValue);
    };

    @Override
    public void updateItem(MeetingTask item, boolean empty) {
        super.updateItem(item, empty);
        Platform.runLater(()->{
            if(lastTask!=null) {
                lastTask.typeProperty().removeListener(backgroundChanger);
            }
            lastTask = item;
            if(item!=null) {
                item.typeProperty().addListener(backgroundChanger);
            }
        });
        if(getItem()!=null) {
            setRowColor(this, getItem().getType());
        } else {
            this.setBackground(defaultBackground);
        }
    }

    private void setRowColor(TableRow<MeetingTask> row, MeetingTask.Type type) {
        row.setBackground(getRowBackground(type));
    }

    private Background getRowBackground(MeetingTask.Type type) {
        switch (type) {
            case UNKNOWN: {
                return new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY));
            }
            case NONE: {
                return Background.EMPTY;
            }
            case TREASURES: {
                return new Background(new BackgroundFill(Color.web("#ffca28"), CornerRadii.EMPTY, Insets.EMPTY));
            }
            case MINISTRY: {
                return new Background(new BackgroundFill(Color.web("#ef9a9a"), CornerRadii.EMPTY, Insets.EMPTY));
            }
            case LIVING: {
                return new Background(new BackgroundFill(Color.web("#81c784"), CornerRadii.EMPTY, Insets.EMPTY));
            }
            case LECTURE: {
                return new Background(new BackgroundFill(Color.web("#d1c4e9"), CornerRadii.EMPTY, Insets.EMPTY));
            }
            case WATCHTOWER: {
                return new Background(new BackgroundFill(Color.web("#81d4fa"), CornerRadii.EMPTY, Insets.EMPTY));
            }
            case OVERSEER: {
                return new Background(new BackgroundFill(Color.web("#ffee58"), CornerRadii.EMPTY, Insets.EMPTY));
            }
            case OTHER: {
                return new Background(new BackgroundFill(Color.web("#b0bec5"), CornerRadii.EMPTY, Insets.EMPTY));
            }
            default: {
                return Background.EMPTY;
            }
        }
    }
}
