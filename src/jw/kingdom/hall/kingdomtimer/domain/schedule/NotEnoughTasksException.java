package jw.kingdom.hall.kingdomtimer.domain.schedule;

/**
 * All rights reserved & copyright ©
 */
public class NotEnoughTasksException extends Throwable {
    NotEnoughTasksException(){
        super("Cannot return MeetingTask for selected index. It's not enough tasks in the list.");
    }
}
