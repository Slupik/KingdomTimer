package jw.kingdom.hall.kingdomtimer.entity.task;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface TaskContainer {
    List<Task> getList();
    void setList(List<Task> task);
    void clear();
    void remove(String id);
    void add(Task task);
    void update(Task task);
    Task get(String id);
}
