package com.ake.nbems.eaps.test;

import com.ake.nbems.eaps.netty.util.ChannelMapUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author lium
 * @Date 2022/3/23
 * @Description
 */

public class TestNettySend {

    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        obj.put("name", "张三");
        obj.put("age", 18);
        ChannelMapUtils.writeMessage("1", obj, 10);
    }
}
