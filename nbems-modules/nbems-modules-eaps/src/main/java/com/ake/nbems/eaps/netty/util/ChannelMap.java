package com.ake.nbems.eaps.netty.util;

import io.netty.channel.Channel;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelMap {
	
	public volatile static int channelNum=0;
    public volatile static int gwCodeNum=0;
    //concurrentHashmap以解决多线程冲突，保存channelId和Channel对象
    private volatile static ConcurrentHashMap<String,Channel> channelHashMap = null;

    public static ConcurrentHashMap<String, Channel> getChannelHashMap() {
        return channelHashMap;
    }
    

    public static Channel getChannelByCode(String channelId){
        if(channelHashMap==null||channelHashMap.isEmpty()){
            return null;
        }
        return channelHashMap.get(channelId);
    }
    
    public static void addChannel(String channelId,Channel channel){
        if(channelHashMap == null) {
            synchronized (ChannelMap.class) {
                if (channelHashMap == null) {
                    channelHashMap = new ConcurrentHashMap<>();
                }
            }
        }
        synchronized ( ChannelMap.class) {
            if (!channelHashMap.containsKey(channelId)) {
                channelNum++;
            }
            channelHashMap.put(channelId,channel);

        }
    }
    
    public static int removeChannelByChannelId(String channelId){
        synchronized ( ChannelMap.class) {
            if (channelHashMap.containsKey(channelId)) {
                channelHashMap.remove(channelId);
                channelNum--;
                return 0;
            } else {
                return 1;
            }
        }
    }
    
    public static String getAllgwCode(){
    	StringBuffer gwCodeAll = new StringBuffer();
    	if(channelHashMap!=null) {
    		for(Entry<String, Channel> entry : channelHashMap.entrySet()){
        	    String mapKey = entry.getKey();
        	    gwCodeAll.append(mapKey).append(",");
        	    System.out.println(mapKey);
        	}
    	}
    	return gwCodeAll.toString();
    }
	
}
