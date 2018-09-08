package jw.kingdom.hall.kingdomtimer.entity.time.countdown;

import javafx.scene.paint.Paint;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;

/**
 * All rights reserved & copyright ©
 */
public interface TimeDisplay {
    void onTaskChange(Task newTask);
    void display(int startTime, int timeToDisplay, int absoluteTimeLeft);
    void setLightBackground(boolean lightBackground);
    void setTextColor(Paint fill);
    void resetColorToLast();
    void reset();
}
