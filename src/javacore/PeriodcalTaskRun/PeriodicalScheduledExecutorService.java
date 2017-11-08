package javacore.PeriodcalTaskRun;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by tsf on 17-11-8.
 */


public class PeriodicalScheduledExecutorService implements Runnable {

    // start a thread
    @Override
    public void run() {
        while (true) {
            // task for run
            System.out.println("Hello, iflytang, now is " + new Date());
        }
    }

    public static void main(String[] args) {
        final long delay = 0;
        final long intervalTime = 3 * 1000;
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new PeriodicalScheduledExecutorService(), delay, intervalTime, TimeUnit.MILLISECONDS);
    }
}
