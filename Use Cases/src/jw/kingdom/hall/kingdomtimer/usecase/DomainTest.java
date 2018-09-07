package jw.kingdom.hall.kingdomtimer.usecase;

import jw.kingdom.hall.kingdomtimer.usecase.time.countdown.CountdownControllerImpl;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.TimeDisplay;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownController;

/**
 * All rights reserved & copyright ©
 */
public class DomainTest {
    public static void main(String[] args) throws Exception {
        testCountdown();
    }

    private static void testCountdown() throws Exception {
        CountdownController controller = new CountdownControllerImpl();
        controller.addTimeDisplay(new TimeDisplay() {
            @Override
            public void onTaskChange(Task newTask) {
                System.out.println("New task: "+newTask.getName()+ "("+newTask.getSeconds()+")");
            }

            @Override
            public void display(int startTime, int time) {
                System.out.println(time);
            }

            @Override
            public void setLightBackground(boolean lightBackground) {

            }

            @Override
            public void resetColorToLast() {

            }

            @Override
            public void reset() {

            }
        });

        Task task = new TaskBean();
        task.setDirectDown(false);
        task.setSeconds(5);
        controller.start(task);
        Thread.sleep(3000);
        controller.addTime(5);
    }
}
