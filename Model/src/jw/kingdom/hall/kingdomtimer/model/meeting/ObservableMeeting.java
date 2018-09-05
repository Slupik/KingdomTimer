/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.model.meeting;

import jw.kingdom.hall.kingdomtimer.model.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.model.observable.field.ObservableField;
import jw.kingdom.hall.kingdomtimer.model.observable.list.ObservableArray;

public interface ObservableMeeting extends Meeting {
    ObservableField<String> nameProperty();
    ObservableField<MeetingType> typeProperty();
    ObservableArray<ObservableTask> tasksProperty();
}
