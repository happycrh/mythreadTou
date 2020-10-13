package com.happycrh.threadtour.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

public class MyTask extends RecursiveAction {
    RecursiveTask d;

    private long[] list;

    private static AtomicLong atomicLong = new AtomicLong(0l);
    private static AtomicLong atomicLong_signel = new AtomicLong(0l);

    // 每个"小任务"最多执行保存50个数

    private static int MAX = 50;


    private int start;

    private int end;


    MyTask(int start, int end, long[] list) {

        this.start = start;

        this.end = end;
        this.list = list;

    }
    @Override
    protected void compute() {
        // 当end-start的值小于MAX时候，开始执行
        if ((end - start) < MAX) {
            for (int i = start; i <= end; i++) {
                long assemblyLog = list[i];
                try {
                    Thread.sleep(1);
                    //System.out.println(5555);
                    //System.out.println("多currentThread=" + Thread.currentThread().getName() + ",assemblyLog=" + assemblyLog);
                    //System.out.println("atomicLong.longValue()="+atomicLong.longValue());
                    atomicLong.incrementAndGet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            // 将大任务分解成两个小任务，大于临界值继续进行拆分子任务
            int middle = (start + end) / 2;
            // 拆分子任务
            MyTask left = new MyTask(start, middle, list);
            // 拆分子任务
            MyTask right = new MyTask(middle + 1, end, list);
            // 并行执行两个小任务
            left.fork();
            right.fork();
            left.join();
            right.join();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        long[] numbers = LongStream.range(1, 20000l).toArray();
        toDo(numbers);
    }

    public static void toDo(long[] list) throws InterruptedException {

        System.err.println("*****************************单线程程序开始执行*****************************");
        long start_signel = System.currentTimeMillis();
        for (long i : list) {
            Thread.sleep(1);
            //System.out.println("单currentThread=" + Thread.currentThread().getName() + ",assemblyLog=" + i);
            atomicLong_signel.incrementAndGet();
        }

        long end_signel = System.currentTimeMillis();
        System.err.println("单线程耗时：" + (end_signel - start_signel));
        System.err.println("atomicLong_signel:" + atomicLong_signel);
        System.err.println("*****************************单线程程序结束执行*****************************");


        System.err.println("*****************************多线程程序开始执行*****************************");
        // 创建线程池，包含Runtime.getRuntime().availableProcessors()返回值作为个数的并行线程的ForkJoinPool
        System.out.println("Runtime.getRuntime().availableProcessors():"+Runtime.getRuntime().availableProcessors());
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long start = System.currentTimeMillis();
        try {
            // 提交可拆分的Task任务
            forkJoinPool.invoke(new MyTask(0, list.length - 1, list));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 关闭线程池
        forkJoinPool.shutdown();
        long end = System.currentTimeMillis();
        System.err.println("多线程耗时：" + (end - start));
        System.err.println("atomicLong:" + atomicLong);
        //System.err.println("单线程耗时：" + (end_signel - start_signel));
       // System.err.println("atomicLong_signel:" + atomicLong_signel);
        System.err.println("*****************************多线程程序执行结束*****************************");

    }
}