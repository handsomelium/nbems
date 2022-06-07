package com.ake.nbems.eaps.test;

import com.ake.nbems.eaps.NbemsEapsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Author lium
 * @Date 2022/6/7
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NbemsEapsApplication.class)
public class TestRedis {

    @Autowired
    public RedisTemplate redisTemplate;


    @Test
    public void setRedis(){
        redisTemplate.opsForValue().set("name", "lium");
        redisTemplate.opsForValue().set("age", 25);
    }

    @Test
    public void getRedis(){
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

}
