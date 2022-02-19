package com.ake.common.redis.utils;

import com.ake.common.redis.domain.SysDictData;
import com.ake.common.redis.service.RedisService;
import com.ake.nbems.common.core.constant.Constants;
import com.ake.nbems.common.core.utils.SpringUtils;
import com.ake.nbems.common.core.utils.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * 字典工具类
 * 
 * @author guojm
 */
public class DictUtils
{
    /**
     * 设置字典缓存
     * 
     * @param key 参数键
     * @param dictDatas 字典数据列表
     */
    public static void setDictCache(String key, List<SysDictData> dictDatas)
    {
        SpringUtils.getBean(RedisService.class).setCacheObject(getCacheKey(key), dictDatas);
    }

    /**
     * 获取字典缓存
     * 
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    public static List<SysDictData> getDictCache(String key)
    {
        Object cacheObj = SpringUtils.getBean(RedisService.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj))
        {
            List<SysDictData> dictDatas = StringUtils.cast(cacheObj);
            return dictDatas;
        }
        return null;
    }
    
    
    /**
     * 获取字典label
     * 
     * @param key 参数键
     * @param value
     * @return SysDictData 字典数据
     */
    public static SysDictData getDictData(String key, String value)
    {
        Object cacheObj = SpringUtils.getBean(RedisService.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj))
        {
            List<SysDictData> dictDatas = StringUtils.cast(cacheObj);
            for(SysDictData dict : dictDatas) {
            	if(dict.getDictValue().equals(value)) {
            		return dict;
            	}
            }
        }
        return null;
    }

    /**
     * 清空字典缓存
     */
    public static void clearDictCache()
    {
        Collection<String> keys = SpringUtils.getBean(RedisService.class).keys(Constants.SYS_DICT_KEY + "*");
        SpringUtils.getBean(RedisService.class).deleteObject(keys);
    }

    /**
     * 设置cache key
     * 
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey)
    {
        return Constants.SYS_DICT_KEY + configKey;
    }
}
