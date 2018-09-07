/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.entity.meeting;

import jw.kingdom.hall.kingdomtimer.entity.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.entity.observable.field.ObservableField;
import jw.kingdom.hall.kingdomtimer.entity.observable.list.ObservableArray;

public interface ObservableMeeting extends Meeting {
    ObservableField<String> nameProperty();
    ObservableField<MeetingType> typeProperty();
    ObservableArray<ObservableTask> tasksProperty();
}
