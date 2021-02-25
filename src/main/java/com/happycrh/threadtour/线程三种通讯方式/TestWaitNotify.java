package com.happycrh.threadtour.线程三种通讯方式;

/**
 * @author chenrenhui
 * @version 0.0.1
 * @date 2021/2/24 17:21
 * @desc
 * objectLock.wait(); wait,notify,notifyAll方法只能由同一对象锁持有的线程调用，
 * 也就是写在同步块或同步方法中，否则会抛出IllegalMonitorStateException异常
 *
 * wait方法使当前线程等待，加入该对象的等待池中，并且放弃持有的该对象锁
 * notify,notifyAll唤醒一个或所有正在等待这个对象锁的线程；
 */
public class TestWaitNotify {

    public static Object testObject = null;
    public static Object objectLock = new Object();


    public static void main(String[] args) throws Exception {
        //new TestWaitNotify().waitnotifyTest();
        new TestWaitNotify().waitnotifyDeadlockTest();
    }

    public void waitnotifyTest() throws Exception {
        Thread customerThread = new Thread(() -> {
            try {
                while (testObject == null) {
                    System.out.println("1 进入等待");
                    synchronized (objectLock) {
                        objectLock.wait();
                    }
                }
                System.out.println("2 获得对象");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        customerThread.start();
        //三秒之后产生对象
        Thread.sleep(1000);
        //三秒之后产生对象
        testObject = new Object();
        synchronized (objectLock) {
            objectLock.notify();
        }
        System.out.println("3 通知消费者");
    }

    /**
     * wait 调用顺序导致的死锁，会使线程一直处于waiting状态
     * @throws Exception
     */
    public void waitnotifyDeadlockTest() throws Exception {
        Thread customerThread = new Thread(() -> {
            try {
                while (testObject == null) {
                    Thread.sleep(2000);
                    synchronized (objectLock) {
                        System.out.println("1 进入等待");
                        objectLock.wait();
                    }
                }
                System.out.println("2 获得对象");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        customerThread.start();
        //三秒之后产生对象
        Thread.sleep(1000);
        //三秒之后产生对象
        testObject = new Object();
        synchronized (objectLock) {
            objectLock.notify();
        }
        System.out.println("3 通知消费者");
    }
}
