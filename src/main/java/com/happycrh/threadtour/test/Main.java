package com.happycrh.threadtour.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenrenhui
 * @version 0.0.1
 * @date 2020/10/13 15:20
 * @desc
 */
public class Main {

    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add(1);
        Integer oldCapacity = 6;
        System.out.println(oldCapacity >> 1);
        System.out.println(oldCapacity);
        System.out.println(oldCapacity << 3);
    }

}
