package com.ake.nbems.eaps.service;

/**
 * @Author lium
 * @Date 2022/6/8
 * @Description
 */

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async("threadPoolTaskExecutor")
    public void exect(){
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("执行异步线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
