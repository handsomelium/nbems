package com.ake.nbems.eaps.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author lium
 * @Date 2022/4/11
 * @Description
 */
public class ThreadTest_singleton_test {

    private final static ExecutorService executors = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++){
            executors.execute(() -> {
                System.out.println(ThreadTest_singleton.getSingleton());
            });
        }

        executors.shutdown();

    }

}
