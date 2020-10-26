package com.happycrh.threadtour.lock;

/**
 * @author chenrenhui
 * @version 0.0.1
 * @date 2020/10/13 17:19
 * @desc
 */
public class HoldsLockMain {
    public static void main(String[] args) throws Exception {
        HoldsLockMain main = new HoldsLockMain();
        main.t();
        main.test1();
        System.out.println("==="+Thread.holdsLock(HoldsLockMain.class));

    }

    public static synchronized void t() throws InterruptedException {
        System.out.println("---"+Thread.holdsLock(HoldsLockMain.class));
    }


    Object o = new Object();

    public void test1() throws Exception {

        new Thread(() -> {
            synchronized (o) {
                System.out.println("child thread: holdLock: " +
                        Thread.holdsLock(o));
            }
        }).start();

        new Thread(() -> {
            t2();

        }).start();
        System.out.println("main thread: holdLock: " + Thread.holdsLock(o));
        Thread.sleep(2000);
    }

    public static synchronized void t2(){
        System.out.println("child thread--: holdLock: " +
                Thread.holdsLock(HoldsLockMain.class));
    }
}
