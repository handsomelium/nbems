package com.ake.nbems.eaps.test;

import com.ake.nbems.eaps.NbemsEapsApplication;
import com.ake.nbems.eaps.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author lium
 * @Date 2022/6/7
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NbemsEapsApplication.class)
public class TestRedis {

    @Autowired
    public RedisUtil redisUtil;


    @Test
    public void setRedis(){
        redisUtil.set("name", "张三");
        redisUtil.set("age", 18);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 18);
        map.put("sex", "男");
        map.put("score", 90);
        redisUtil.hmset("student", map);
    }

    @Test
    public void getRedis(){
        Object name = redisUtil.get("name");
        System.out.println(name);
    }

}
