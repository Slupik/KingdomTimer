/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.entity.task;

import jw.kingdom.hall.kingdomtimer.entity.observable.field.ObservableField;

public interface ObservableTask extends Task {
    ObservableField<String> nameProperty();
    ObservableField<Integer> secondsProperty();
    ObservableField<Boolean> directDownProperty();
    ObservableField<Boolean> studentTalkProperty();
    ObservableField<Boolean> useBuzzerProperty();
    ObservableField<TaskType> typeProperty();
}
