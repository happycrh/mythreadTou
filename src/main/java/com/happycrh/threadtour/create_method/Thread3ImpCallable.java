package com.happycrh.threadtour.create_method;

import java.util.concurrent.Callable;

/**
 * @author chenrenhui
 * @version 0.0.1
 * @date 2020/10/12 11:16
 * @desc
 */
public class Thread3ImpCallable implements Callable {

    @Override
    public Object call() {
        System.out.println("thread3 running");
        if (Math.random() < 0.5) {
            throw new RuntimeException("运行时异常！");
        }
        return 465;
    }
}
