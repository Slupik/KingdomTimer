/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.model.task;

import jw.kingdom.hall.kingdomtimer.model.observable.field.ObservableField;

public interface ObservableTask extends Task {
    ObservableField<String> nameProperty();
    ObservableField<Integer> secondsProperty();
    ObservableField<Boolean> directDownProperty();
    ObservableField<Boolean> studentTalkProperty();
    ObservableField<Boolean> useBuzzerProperty();
    ObservableField<TaskType> typeProperty();
}
