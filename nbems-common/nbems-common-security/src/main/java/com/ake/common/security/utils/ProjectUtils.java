package com.ake.common.security.utils;

import com.ake.common.redis.service.RedisService;
import com.ake.nbems.common.core.constant.Constants;
import com.ake.nbems.common.core.constant.HttpStatus;
import com.ake.nbems.common.core.exception.CustomException;
import com.ake.nbems.common.core.utils.SpringUtils;
import com.ake.nbems.common.core.utils.StringUtils;

/**
 * @title: ProjectUtils.java
 * @description: 
 * @copyright: AKE
 * 
 * @author 2020年9月24日
 * @date 2020年9月24日
 */
public class ProjectUtils {

	/**
     * 获取当前项目编码
     */
    public static String getCurrentProjectCode() {
    	if(SecurityUtils.getAuthentication()==null) {
    		throw new CustomException("请登录", HttpStatus.UNAUTHORIZED);
    	}
    	String username = SecurityUtils.getUsername();
    	String projectCode = SpringUtils.getBean(RedisService.class).getCacheObject(Constants.SYS_CURRENTPROJECT_KEY+username);
    	if(StringUtils.isEmpty(projectCode)) {
    		projectCode=SecurityUtils.getLoginUser().getProjectCode();
    	}
        return projectCode;
    }
}
