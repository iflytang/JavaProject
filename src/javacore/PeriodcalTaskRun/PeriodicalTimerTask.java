package javacore.PeriodcalTaskRun;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tsf on 17-11-8.
 *
 * @Description Timer for scheduling, and TimerTask is used for enclosing task to be executed inside its run() method.
 *              It's thread-safe.
 */


public class PeriodicalTimerTask {

    private class EventTimerTask extends TimerTask {
        @Override
        public void run() {
            // task for run
            System.out.println("Hello, iflytang, now is " + new Date());
        }
    }

    // time schedule
    public void timerSchedule() {
        final long delay = 0;
        final long intervalTime = 3 * 1000;
        EventTimerTask task = new EventTimerTask();

        Timer timer = new Timer();
        timer.schedule(task, delay, intervalTime);
    }

    public static void main(String[] args) {
        PeriodicalTimerTask task = new PeriodicalTimerTask();
        task.timerSchedule();
    }
}
