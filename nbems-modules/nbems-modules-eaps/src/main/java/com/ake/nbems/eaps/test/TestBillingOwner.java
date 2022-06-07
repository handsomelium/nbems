package com.ake.nbems.eaps.test;

import com.ake.nbems.eaps.NbemsEapsApplication;
import com.ake.nbems.eaps.domain.BillingOwner;
import com.ake.nbems.eaps.mapper.TestMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

/**
 * @Author lium
 * @Date 2022/4/8
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NbemsEapsApplication.class)
public class TestBillingOwner {

    /**
     * 使用静态变量记住一个线程池对象
     */
    private static final ExecutorService POOL = new ThreadPoolExecutor(300,
            1500, 6, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


    // ExecutorService POOL = Executors.newFixedThreadPool(5);

    @Autowired
    private TestMapper testMapper;

    @Test
    public void inserOwner1() throws InterruptedException {
        long startTime = System.currentTimeMillis();


        for (int i = 1; i <= 1000; i++){
            int finalI = i;
            POOL.execute(() -> {

                System.out.println(finalI);
                BillingOwner billingOwner = new BillingOwner();
                billingOwner.setOwnerCode(String.valueOf(finalI));
                billingOwner.setOwnerName("业主" + finalI);
                testMapper.insert(billingOwner);
                // testMapper.insertOwnerInfo(billingOwner);

            });


        }

        POOL.shutdown();

        while (true){
            if (POOL.isTerminated()){
                System.out.println("用时============》" + (System.currentTimeMillis() - startTime));
                return;
            }
        }



    }


    @Test
    public void inserOwner2(){
        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= 1000; i++){
            BillingOwner billingOwner = new BillingOwner();
            billingOwner.setOwnerCode(String.valueOf(i));
            billingOwner.setOwnerName("业主" + i);
            // testMapper.insert(billingOwner);
            testMapper.insertOwnerInfo(billingOwner);
        }

        long endTime = System.currentTimeMillis();

        System.out.println(endTime-startTime);

    }

    @Test
    public void deleteOwner(){
        QueryWrapper<BillingOwner> param = new QueryWrapper<>();
        param.isNull("area_id");
        testMapper.delete(param);

    }


}
