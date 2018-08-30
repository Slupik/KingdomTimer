package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl.table;

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
        updateRowColors();
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
                case OVERSEER: {
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
                    toFill = Color.web("#ffca28");
                }
                break;
            }
            case MINISTRY: {
                if(isDarkLayout) {
                    toFill = Color.web("#c18626");
                } else {
                    toFill = Color.web("#ef9a9a");
                }
                break;
            }
            case LIVING: {
                if(isDarkLayout) {
                    toFill = Color.web("#961526");
                } else {
                    toFill = Color.web("#81c784");
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
            case OVERSEER: {
                if(isDarkLayout) {
                    toFill = Color.DARKORCHID;
                } else {
                    toFill = Color.web("ffee58");
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
