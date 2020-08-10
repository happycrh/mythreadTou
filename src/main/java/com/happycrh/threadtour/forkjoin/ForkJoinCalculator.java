package com.happycrh.threadtour.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author qhong
 * @date 2018/4/18 16:14
 * @description
 **/
public class ForkJoinCalculator {
    private ForkJoinPool pool;

    public ForkJoinCalculator() {
        // 也可以使用公用的 ForkJoinPool：
        // pool = ForkJoinPool.commonPool()
        pool = new ForkJoinPool();
    }

    public static void main(String[] args) {
        ForkJoinCalculator forkJoinCalculator=new ForkJoinCalculator();
        long[] numbers=LongStream.range(1,20).toArray();
        System.out.println(Arrays.toString(numbers));
        long result=forkJoinCalculator.sumUp(numbers);
        System.out.println("result:"+result);
    }

    private static class SumTask extends RecursiveTask<Long> {
        private long[] numbers;
        private int from;
        private int to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            // 当需要计算的数字小于6时，直接计算结果
            if (to - from < 4) {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                System.out.println(String.format("currentThread:%s,total:%s,from:%s,to:%s",Thread.currentThread().getName(),total,from,to));

                return total;
                // 否则，把任务一分为二，递归计算
            } else {
                int middle = (from + to) / 2;
                SumTask taskLeft = new SumTask(numbers, from, middle);
                SumTask taskRight = new SumTask(numbers, middle+1, to);
                taskLeft.fork();
                taskRight.fork();
                return taskLeft.join() + taskRight.join();
            }
        }
    }

    public long sumUp(long[] numbers) {
        return pool.invoke(new SumTask(numbers, 0, numbers.length-1));
    }
}