package jw.kingdom.hall.kingdomtimer.entity.task;

import jw.kingdom.hall.kingdomtimer.entity.observable.list.ListListenerAddable;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
//TODO move to schedule or delete
/*
 + lower processor usage on refresh list
  - much more work to implement and maintain
 */
public interface TaskContainer extends ListListenerAddable<Task> {
    List<Task> getList();
    void setList(List<Task> task);
    void clear();
    void remove(String id);
    void add(Task task);
    void update(Task task);
    Task get(String id);
    Task get(int index);
}
