package com.happycrh.threadtour.create_method;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author chenrenhui
 * @version 0.0.1
 * @date 2020/10/12 11:17
 * @desc
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread1Extends thread1Extends = new Thread1Extends();
        Thread2Iml thread2Iml = new Thread2Iml();
        Thread3ImpCallable thread3ImpCallable = new Thread3ImpCallable();
        Thread thread = new Thread(thread2Iml);
        FutureTask futureTask = new FutureTask(thread3ImpCallable);
        Thread thread1 = new Thread(futureTask);


        thread1Extends.start();
        thread.start();
        thread1.start();


        System.out.println(futureTask.get());

    }
}
