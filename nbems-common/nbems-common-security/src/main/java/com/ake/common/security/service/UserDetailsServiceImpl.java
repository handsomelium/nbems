package com.ake.common.security.service;

import com.ake.common.security.domain.LoginUser;
import com.ake.common.security.utils.SecurityUtils;
import com.ake.nbems.common.core.domain.R;
import com.ake.nbems.common.core.enums.UserStatus;
import com.ake.nbems.common.core.utils.StringUtils;
import com.ake.system.api.domain.SysUser;
import com.ake.system.api.model.UserInfo;
import com.ake.system.api.service.RemoteUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author yezt
 * @description 用户信息处理
 * @date 2021/12/15 17:01
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        R<UserInfo> userResult = remoteUserService.getUserInfo(username);
        checkUser(userResult, username);
        return getUserDetails(userResult);
    }

    public void checkUser(R<UserInfo> userResult, String username) {
        if (StringUtils.isNotNull(userResult) && StringUtils.isNotBlank(userResult.getMsg())) {
        	//账号未授权项目
        	throw OAuth2Exception.create("noAuth", userResult.getMsg());
        }else if(StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData())) {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(userResult.getData().getSysUser().getDelFlag())) {
            log.info("登录用户：{} 已被删除.", username);
            throw OAuth2Exception.create("accountDel","对不起，您的账号：" + username + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(userResult.getData().getSysUser().getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw OAuth2Exception.create("accountStop","对不起，您的账号：" + username + " 已停用");
        }
    }

    private UserDetails getUserDetails(R<UserInfo> result) {
        UserInfo info = result.getData();
        Set<String> dbAuthsSet = new HashSet<String>();
        if (StringUtils.isNotEmpty(info.getRoles())) {
            // 获取角色
            dbAuthsSet.addAll(info.getRoles());
            // 获取权限
            dbAuthsSet.addAll(info.getPermissions());
        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUser user = info.getSysUser();
        return new LoginUser(user.getId(), user.getUserName(), user.getPassword(), true, true, true, true,
                authorities, user.getProjectCode(), false,false);
    }

}
