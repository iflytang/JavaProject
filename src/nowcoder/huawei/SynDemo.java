package src.nowcoder.huawei;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynDemo{

    public static void main(String[] arg){
        Lock lock=new ReentrantLock();

        Runnable t1 = new MyThread2("A", 0, lock);
        Runnable t2 = new MyThread2("B", 1, lock);
        Runnable t3 = new MyThread2("C", 2, lock);
        new Thread(t1,"t1").start();
        new Thread(t2,"t2").start();
        new Thread(t3,"t3").start();
    }

}
class MyThread implements Runnable {

    private Lock lock=new ReentrantLock();
    public void run() {
        lock.lock();
        try{
            for(int i=0;i<5;i++)
                System.out.println(Thread.currentThread().getName()+":"+i);
        }finally{
            lock.unlock();
        }
    }

}

class MyThread2 implements Runnable {
    int state;
    String name;
    int targetNum;
    int times = 10;
    Lock lock;

    MyThread2(String name, int targetNum, Lock lock) {
        this.name = name;
        this.targetNum = targetNum;
        this.lock = lock;
    }


    public void run() {
        printLetter(name, targetNum);
    }

    private void printLetter(String name, int targetNum) {
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