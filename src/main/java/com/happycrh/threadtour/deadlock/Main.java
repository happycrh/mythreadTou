package com.happycrh.threadtour.deadlock;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenrenhui
 * @version 0.0.1
 * @date 2020/10/30 17:04
 * @desc
 */
public class Main {

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public static void main(String[] args) {

        System.out.println(new Main().te());
    }




    public String te(){
        new Thread(()->{
            synchronized(lock1) {
                try {Thread.sleep(1000);}catch(Exception e) {}
                synchronized(lock2) {
                    System.out.println("Thread1 over");
                }
            }
        }) .start();
        new Thread(()->{
            synchronized(lock2) {
                try {Thread.sleep(1000);}catch(Exception e) {}
                synchronized(lock1) {
                    System.out.println("Thread2 over");
                }
            }
        }) .start();
        return "123";
    }
}
