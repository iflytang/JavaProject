package javacore.KillThread;

import org.omg.CORBA.TIMEOUT;

import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by tsf on 17-11-13.
 */


public class FutureKillThread extends Thread {

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.currentThread().sleep(1 * 1000);
                System.out.println("Hello, iflytang! Now is " + new Date());
            }
            System.out.println("exit the thread loop at " + new Date());
        } catch (InterruptedException e) {
            System.out.println("get Exception: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> task = executorService.submit(new FutureKillThread());
        int timeout = 5;

        try {
            // get the result in limited time
            task.get(timeout, TimeUnit.SECONDS);  // this time is running time of this thread
        } catch (TimeoutException e) {
            // if time out, stop the thread
            System.out.println("the task incurs time out, the thread will be killed, " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("the interruptException happens, " + e.getMessage());
        } catch (ExecutionException e) {
            System.out.println("the ExecutionException happens, " + e.getMessage());
        } finally {
            // if the task run in certain thread, then that thread can be interrupted
            boolean mayInterruptIfRunning = true;
            task.cancel(mayInterruptIfRunning);
        }
    }
}
