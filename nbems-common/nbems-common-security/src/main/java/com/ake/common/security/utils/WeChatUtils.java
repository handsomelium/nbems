package com.ake.common.security.utils;

import com.ake.common.redis.service.RedisService;
import com.ake.nbems.common.core.constant.Constants;
import com.ake.nbems.common.core.utils.SpringUtils;

public class WeChatUtils {
	
	/**
     * 获取微信登录人选择的业主id
     */
    public static Integer getCurrentWeChatOwnerId(String weChatId) {
    	Integer ownerId = SpringUtils.getBean(RedisService.class).getCacheObject(Constants.SYS_WECHATOWNERID_KEY+weChatId);
        return ownerId;
    }
}
