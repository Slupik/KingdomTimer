package jw.kingdom.hall.kingdomtimer.domain.schedule;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class NotEnoughTasksException extends Throwable {
    NotEnoughTasksException(){
        super("Cannot return MeetingTask for selected index. It's not enough tasks in the list.");
    }
}
