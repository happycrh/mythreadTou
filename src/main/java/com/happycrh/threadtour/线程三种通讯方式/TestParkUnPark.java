package com.happycrh.threadtour.线程三种通讯方式;

import java.util.concurrent.locks.LockSupport;

/**
 * @author chenrenhui
 * @version 0.0.1
 * @date 2021/2/24 18:00
 * @desc
 */
public class TestParkUnPark {
    Object object = null;

    public static void main(String[] args) throws Exception {
        new TestParkUnPark().parkunparkTest();
//        new TestParkUnPark().parkunparkDeadlockTest();
    }

    /**
     * park,unpark不需要像wait，notify一样保证顺序
     * @throws Exception
     */
    public void parkunparkTest() throws Exception {
        Thread currentThread = new Thread(() -> {
            while (object == null) {
                System.out.println("1 进入等待");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.park();
            }
            System.out.println("2 获取对象");
        });
        currentThread.start();
        Thread.sleep(1000);
        object = new Object();
        LockSupport.unpark(currentThread);
        System.out.println("3 通知消费者");
    }

    /**
     * park,unpark不会释放锁
     * @throws Exception
     */
    public void parkunparkDeadlockTest() throws Exception {
        Thread currentThread = new Thread(() -> {
            while (object == null) {
                System.out.println("1 进入等待");
                synchronized (this) {
                    LockSupport.park();
                }
            }
            System.out.println("2 获取对象");
        });
        currentThread.start();
        Thread.sleep(1000);
        object = new Object();
        synchronized (this) {
            LockSupport.unpark(currentThread);
        }
        System.out.println("3 通知消费者");
    }
}
