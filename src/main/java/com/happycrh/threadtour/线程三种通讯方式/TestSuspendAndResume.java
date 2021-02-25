package com.happycrh.threadtour.线程三种通讯方式;

/**
 * @author chenrenhui
 * @version 0.0.1
 * @date 2021/2/24 16:53
 * @desc
 */
public class TestSuspendAndResume {

    Object testObject = null;

    public static void main(String[] args) throws Exception {
        new TestSuspendAndResume().suspendResumeTest();
//        new TestSuspendAndResume().suspendResumeDeadlockTest();
//        new TestSuspendAndResume().suspendResumeDeadlockTest2();
    }

    public void suspendResumeTest() throws InterruptedException {
        Thread cunsumerThread = new Thread(() -> {
            System.out.println("1 进入等待");
            while (testObject == null) {
                //阻塞线程
                Thread.currentThread().suspend();
            }
            System.out.println("2 获取对象");
        });
        cunsumerThread.start();
        //三秒之后产生对象
        Thread.sleep(1000);
        testObject = new Object();
        //唤醒线程
        cunsumerThread.resume();
        //主线程、与cunsumerThread线程谁先执行不一定
        System.out.println("3 通知消费者");
    }

    /**
     * suspend,resume 执行顺序问题导致的死锁
     * @throws Exception
     */
    public void suspendResumeDeadlockTest() throws Exception {
        Thread cunsumerThread = new Thread(() -> {
            System.out.println("1 进入等待");
            while (testObject == null) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //阻塞线程
                Thread.currentThread().suspend();
            }
            System.out.println("2 获取对象");
        });
        cunsumerThread.start();
        //三秒之后产生对象
        Thread.sleep(1000);
        testObject = new Object();
        //唤醒线程
        cunsumerThread.resume();
        //主线程、与cunsumerThread线程谁先执行不一定
        System.out.println("3 通知消费者");
    }

    /**
     * suspend 不像wait方法能释放锁，导致suspend与resume很容易写出死锁
     * @throws Exception
     */
    public void suspendResumeDeadlockTest2() throws Exception {
        Thread cunsumerThread = new Thread(() -> {
            System.out.println("1 进入等待");
            while (testObject == null) {
                synchronized (this) {
                    //阻塞线程
                    Thread.currentThread().suspend();
                }
            }
            System.out.println("2 获取对象");
        });
        cunsumerThread.start();
        //三秒之后产生对象
        Thread.sleep(1000);
        testObject = new Object();
        //唤醒线程
        synchronized (this) {
            cunsumerThread.resume();
        }
        //主线程、与cunsumerThread线程谁先执行不一定
        System.out.println("3 通知消费者");
    }

}
