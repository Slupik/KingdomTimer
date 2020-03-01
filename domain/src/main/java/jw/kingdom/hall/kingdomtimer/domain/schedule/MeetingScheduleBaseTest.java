/*
 * Feel free to use
 */

package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MeetingScheduleBaseTest {

    private MeetingSchedule sut = new MeetingSchedule();

    @Test
    void changeItemsOrder() {
        TaskBean task0 = Mockito.mock(TaskBean.class);
        TaskBean task1 = Mockito.mock(TaskBean.class);
        TaskBean task2 = Mockito.mock(TaskBean.class);
        TaskBean task3 = Mockito.mock(TaskBean.class);
        TaskBean task4 = Mockito.mock(TaskBean.class);
        sut.addTask(task0);
        sut.addTask(task1);
        sut.addTask(task2);
        sut.addTask(task3);
        sut.addTask(task4);

        sut.moveElement(1, 3);

        assertEquals(
                sut.getList().get(0),
                task0
        );
        assertEquals(
                sut.getList().get(1),
                task2
        );
        assertEquals(
                sut.getList().get(2),
                task3
        );
        assertEquals(
                sut.getList().get(3),
                task1
        );
        assertEquals(
                sut.getList().get(4),
                task4
        );
    }

}