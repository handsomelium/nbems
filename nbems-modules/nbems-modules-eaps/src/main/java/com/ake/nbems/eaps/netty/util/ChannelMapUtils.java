package com.ake.nbems.eaps.netty.util;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;


import com.ake.nbems.common.core.utils.StringUtils;
import com.ake.nbems.eaps.constant.FormatConstants;
import com.ake.nbems.eaps.domain.ResultBO;
import com.alibaba.fastjson.JSONObject;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ChannelMapUtils {

    // 数据包存储
    private static final AttributeKey<StringBuffer> dataBuffer = AttributeKey.valueOf("dataBuffer");

    /**
     * 协议通讯 设置响应时间timeout 1s/2s/3s 单位是s
     */
    public static ResultBO writeMessage(String gateway, JSONObject msg, Integer timeout) {
        ResultBO resultBO = new ResultBO();
        resultBO.setResult(false);
        resultBO.setMsg("获取响应失败");
        if (!StringUtils.isEmpty(gateway) && msg != null) {
            try {
                ConcurrentHashMap<String, Channel> map = ChannelMap.getChannelHashMap();
                if ( map != null && map.containsKey(gateway)) {
                    Channel channel = map.get(gateway);
                    if (channel != null) {
                        String key = createOnlySession(gateway,timeout);
                        msg.put("session",key);

                        // 发送消息
                        /*Attribute<StringBuffer> attrBuffer = channel.attr(dataBuffer);
                        StringBuffer dataBuf = attrBuffer.get();
                        dataBuf.append(msg);
                        channel.writeAndFlush(dataBuf.toString()+"\n");*/
                        channel.writeAndFlush(msg.toString()+"\n");


                        AttributeKey<Object> responseMsg = AttributeKey.valueOf(key);
                        Attribute<Object> attr = channel.attr(responseMsg);
                        synchronized (attr) {
                            attr.wait(timeout * 1000);
                            Object object = attr.get();
                            attr.set(null);
                            if (object != null) {
                                resultBO.setResult(true);
                                resultBO.setData(object);
                                resultBO.setMsg("获取响应成功");
                            }
                        }
                    } else {
                        resultBO.setMsg("channel为null");
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return resultBO;
    }

    public static String createOnlySession(String gateway, Integer timeout) {
        StringBuffer sb = new StringBuffer();
        Integer random = (int)((Math.random()) * 100000);
        // 网关+随机数+超时时间+当前日期
        sb.append(gateway).append(random).append(timeout).append(FormatConstants.NOCHAR.format(new Date()));
        return sb.toString();
    }



}


