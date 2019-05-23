package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface ScheduleListener {
    void onRemove(TaskBean task);
    void onRemove(int index, TaskBean removed);
    void onInsert(TaskBean task);
    void onBulkInsert(TaskBean... task);
    void onClear();
    void onReset(List<TaskBean> newList);
    void onMove(int elementIndex, int destIndex);
}
