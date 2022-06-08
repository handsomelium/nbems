package com.ake.nbems.eaps.test;

import com.ake.nbems.eaps.NbemsEapsApplication;
import com.ake.nbems.eaps.service.AsyncService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author lium
 * @Date 2022/6/8
 * @Description @Async注解的方法必须放在独立的类中，不能和调用者同一个类，所在的类也不能有接口
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NbemsEapsApplication.class)
public class TestAsync {

    @Autowired
    private AsyncService asyncService;

    @Test
    public void getAsync(){
        for (int i = 0; i < 5; i++){
            asyncService.exect();
        }
        System.out.println("已执行完毕");

        try {
            // 由于单元测试主线程太快结束， 让其sleep，让子线程全部执行完
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
