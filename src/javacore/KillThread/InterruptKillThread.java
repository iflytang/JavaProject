package javacore.KillThread;

import java.util.Date;

/**
 * Created by tsf on 17-11-13.
 */


public class InterruptKillThread extends Thread {

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.currentThread().sleep(1 * 1000);
                System.out.println("Hello, iflytang! Now is " + new Date());
            }
            System.out.println("exit the thread loop at " + new Date());
        } catch (Exception e) {
            System.out.println("get Exception: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        InterruptKillThread thread = new InterruptKillThread();
        thread.start();

        try {
            Thread.currentThread().sleep(10 * 1000);
            thread.interrupt();   // interrupt the thread, then the InterruptFlag is set from false to true
            System.out.println("begin to kill the thread at " + new Date());
        } catch (Exception e) {
            System.out.println("get InterruptedException: " + e.getMessage());
        }
        System.out.println("have killed the thread at " + new Date());
    }
}
