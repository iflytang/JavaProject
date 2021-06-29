package src.nowcoder.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://developer.aliyun.com/article/776793
 */

public class multiOrderThread {
    Lock lock = new ReentrantLock();

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);

            // 方法一：利用join来实现按顺序打印，535ms
//            joinThread(n);
//            System.out.println();

            // 方法二：利用ReentrantLock来实现按顺序打印，871ms
//            myReentrantLockFun(n);

            // 方法三：利用Object的wait()和notifyAll()来实现按顺序打印，188ms
            myObjectFun(n);
        }
    }

    // 方法一：利用join来实现按顺序打印
    public static void joinThread (int n) {
        for (int i = 0; i < n; i++) {
            Thread joinThread1 = new Thread(new myJoinThread("A"));
            Thread joinThread2 = new Thread(new myJoinThread("B"));
            Thread joinThread3 = new Thread(new myJoinThread("C"));
            Thread joinThread4 = new Thread(new myJoinThread("D"));

            try {
                joinThread1.start();

                joinThread1.join();
                joinThread2.start();

                joinThread2.join();
                joinThread3.start();

                joinThread3.join();
                joinThread4.start();

                joinThread4.join();  // 所有的执行完再进入下一轮循环
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 方法二：ReentrantLock方法
    public static void myReentrantLockFun(int n) {
        myLockThread loopThread = new myLockThread(n);

        new Thread() {
            @Override
            public void run() {
                loopThread.printLetter("B", 1);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                loopThread.printLetter("A", 0);
            }
        }.start();

        Thread thread3 = new Thread() {
            @Override
            public void run() {
                loopThread.printLetter("C", 2);
            }
        };
        try {
            thread3.start();
            thread3.join();
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 方法三：Object.wait()和notify()
    public static void myObjectFun(int n) {
        mySynThread syn = new mySynThread(n);

        new Thread() {
            @Override
            public void run() {
                syn.print("A", 0);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                syn.print("B", 1);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                syn.print("C", 2);
            }
        }.start();

        Thread td = new Thread() {
            @Override
            public void run() {
                syn.print("D", 3);
            }
        };
        td.start();
        try {
            td.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
    }
}

class myJoinThread implements Runnable {

    String str;

    myJoinThread(String str) {
        this.str = str;
    }

    @Override
    public void run() {
        System.out.print(str);
    }
}

class myLockThread {

    private int times; // 控制打印次数
    private int state;   // 当前状态值：保证三个线程之间交替打印
    private Lock lock = new ReentrantLock();

    public myLockThread(int times) {
        this.times = times;
    }

    public void printLetter(String name, int targetNum) {
        for (int i = 0; i < times; ) {
            lock.lock();
            if (state % 3 == targetNum) {
                state++;
                i++;
                System.out.print(name);
            }
            lock.unlock();
        }
    }
}

class mySynThread {
    int times;  // 打印次数
    int state;  // 打印状态

    Object object = new Object();

    public mySynThread(int n) {
        times = n;
    }

    public void print(String str, int targetNum) {
        for (int i = 0; i < times; i++) {
            synchronized (object) {
                while ((state % 4) != targetNum) {
                    try {
                        object.wait(); // 非自己状态，阻塞
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 属于该线程状态，执行完通知所有线程
                System.out.print(str);
                state++;
                object.notifyAll();
            }
        }
    }

}
