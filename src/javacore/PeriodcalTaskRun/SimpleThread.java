package javacore.PeriodcalTaskRun;

import java.util.Date;

/**
 * Created by tsf on 17-11-8.
 *
 * @Description This is very simple, which creates the simple thread puts it in forever with use of while loop and makes
 *              uses of sleep method to put the interval between running.
 */


public class SimpleThread implements Runnable {

    private final long intevalTime = 3 * 1000;

    // start a thread
    @Override
    public void run() {
        while (true) {
            // task for run
            System.out.println("Hello, iflytang, now is " + new Date());
            // interval for pause
            try {
                Thread.currentThread().sleep(intevalTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // create a thread
        Thread thread = new Thread(new SimpleThread());
        thread.start();
    }
}
