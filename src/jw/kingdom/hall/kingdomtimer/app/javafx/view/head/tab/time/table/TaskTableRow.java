package jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.time.table;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import java.beans.PropertyChangeListener;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class TaskTableRow extends TableRow<MeetingTask> {
    private MeetingTask lastTask;
    private Background defaultBackground = null;
    private PropertyChangeListener listener = evt -> {
        if(evt.getPropertyName().equals(MeetingTask.PropertyName.TYPE)) {
            updateRowColors();
        }
    };

    public TaskTableRow(){
        super();
        if(defaultBackground==null) {
            defaultBackground = getBackground();
        }
    }

    private ChangeListener<MeetingTask.Type> backgroundChanger = (observable, oldValue, newValue) -> {
    };

    @Override
    public void updateItem(MeetingTask item, boolean empty) {
        super.updateItem(item, empty);
        Platform.runLater(()->{
            if(lastTask!=null) {
                lastTask.removePropertyChangeListener(listener);
            }
            lastTask = item;
            if(item!=null) {
                lastTask.addPropertyChangeListener(listener);
            }
            updateRowColors();
        });
    }

    private void updateRowColors() {
        if(getItem()!=null) {
            setRowColor(this, getItem().getType());
            setTextColor(this, getItem().getType());
        } else {
            this.setBackground(defaultBackground);
            setTextColor(this, Color.BLACK);
        }
    }

    private static void setTextColor(TaskTableRow row, MeetingTask.Type type) {
        Paint paint = getTextPaint(type, false);
        setTextColor(row, paint);
    }

    private static void setTextColor(TaskTableRow row, Paint paint) {
        row.setTextFill(paint);
        ObservableList<Node> nodes = row.getChildren();
        for(Node node:nodes) {
            TableCell<MeetingTask, ?> cell = ((TableCell<MeetingTask, ?>) node);
            cell.setTextFill(paint);
        }
    }

    private static Paint getTextPaint(MeetingTask.Type type, boolean isDarkLayout) {
        if(isDarkLayout) {
            switch (type) {
                case UNKNOWN: {
                    return Color.RED;
                }
                case NONE: {
                    return Color.DARKGRAY;
                }
                case TREASURES: {
                    return Color.WHITE;
                }
                case MINISTRY: {
                    return Color.WHITE;
                }
                case LIVING: {
                    return Color.WHITE;
                }
                case LECTURE: {
                    return Color.DARKGREEN;
                }
                case WATCHTOWER: {
                    return Color.WHITE;
                }
                case CIRCUIT: {
                    return Color.WHITE;
                }
                case OTHER: {
                    return Color.DARKGRAY;
                }
                default: {
                    return Color.RED;
                }
            }
        } else {
            return Color.BLACK;
        }
    }

    private static void setRowColor(TableRow<MeetingTask> row, MeetingTask.Type type) {
        row.setBackground(getRowBackground(type,false));
    }

    private static Background getRowBackground(MeetingTask.Type type, boolean isDarkLayout) {
        Color toFill;
        switch (type) {
            case UNKNOWN: {
                if(isDarkLayout) {
                    toFill = Color.web("#eeeeee");
                } else {
                    toFill = Color.web("#eeeeee");
                }
                break;
            }
            case NONE: {
                return Background.EMPTY;
            }
            case TREASURES: {
                if(isDarkLayout) {
                    toFill = Color.web("#5a6a70");
                } else {
                    toFill = Color.web("#9FADB2");
                }
                break;
            }
            case MINISTRY: {
                if(isDarkLayout) {
                    toFill = Color.web("#c18626");
                } else {
                    toFill = Color.web("#ffca28");
                }
                break;
            }
            case LIVING: {
                if(isDarkLayout) {
                    toFill = Color.web("#961526");
                } else {
                    toFill = Color.web("#E87878");
                }
                break;
            }
            case LECTURE: {
                if(isDarkLayout) {
                    toFill = Color.DARKGREEN;
                } else {
                    toFill = Color.web("#d1c4e9");
                }
                break;
            }
            case WATCHTOWER: {
                if(isDarkLayout) {
                    toFill = Color.DARKBLUE;
                } else {
                    toFill = Color.web("#81d4fa");
                }
                break;
            }
            case CIRCUIT: {
                if(isDarkLayout) {
                    toFill = Color.DARKORCHID;
                } else {
                    toFill = Color.web("#81c784");//old: ffee58
                }
                break;
            }
            case OTHER: {
                if(isDarkLayout) {
                    toFill = Color.web("#eeeeee");
                } else {
                    toFill = Color.web("#b0bec5");
                }
                break;
            }
            default: {
                return Background.EMPTY;
            }
        }
        return new Background(new BackgroundFill(toFill, CornerRadii.EMPTY, Insets.EMPTY));
    }
}
