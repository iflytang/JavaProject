package src.javacore.threads;

public class myThread {
    public static void main (String [] args) throws InterruptedException {
        System.out.println("main start...");
        Thread t = new Thread() {
            public void run() {
                System.out.println("thread run...");
                System.out.println("thread end.");
            }
        };
        t.setPriority(10);
        t.start();
//        t.join();
        System.out.println("main end...");
    }
}

