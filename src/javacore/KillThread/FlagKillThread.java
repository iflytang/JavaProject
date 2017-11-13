package javacore.KillThread;

import java.util.Date;

/**
 * Created by tsf on 17-11-13.
 */


public class FlagKillThread extends Thread {

    public volatile boolean enable = false;  // flag

    @Override
    public void run() {
        while (!enable) {
            System.out.println("The Thread create time is " + new Date());
        }
    }

    public static void main(String[] args) {
        // create and start a thread
        FlagKillThread thread = new FlagKillThread();
        thread.start();

        // stop thread by modify flag enable
        try{
            Thread.currentThread().sleep(10 * 1000);  // sleep the thread
            thread.enable = true;   // set the enable flag as true
            thread.join(); // wait at most ms for this thread to die, a timeout of 0 means to wait forever.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("exit the thread." + new  Date());
    }
}
