package com.ake.system.api.service;

import com.ake.nbems.common.core.constant.ServiceNameConstants;
import com.ake.nbems.common.core.domain.R;
import com.ake.system.api.domain.SysUser;
import com.ake.system.api.factory.RemoteUserFallbackFactory;
import com.ake.system.api.model.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户服务
 * 
 * @author guojm
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface  RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/info/{username}")
    public R<UserInfo> getUserInfo(@PathVariable("username") String username);
    
//    @GetMapping("/user/wxuserinfo")
//    public R<WxOAuth2UserInfo> wxUserInfo(@RequestParam("code") String code) throws WxErrorException;
    
    /**
     * 获取登录用户的信息
     * @return
     */
    @GetMapping(value = "/user/info/getUserInfo")
    public R<SysUser> getUserInfo();
    
    /**
     * 查询当前用户授权区域
     *
     * @return 结果
     */
    @GetMapping(value = "/user/info/checkedArea")
    public R<List<Long>> checkedArea();
    
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
//    @GetMapping(value = "/user/kfxtInfo/{username}")
//    public R<KfxtSysUser> getKfxtUserInfo(@PathVariable("username") String username);
    
}
