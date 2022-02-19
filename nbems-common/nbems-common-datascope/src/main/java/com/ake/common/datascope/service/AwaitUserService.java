package com.ake.common.datascope.service;

import com.ake.common.security.utils.SecurityUtils;
import com.ake.nbems.common.core.domain.R;
import com.ake.nbems.common.core.utils.StringUtils;
import com.ake.system.api.model.UserInfo;
import com.ake.system.api.service.RemoteUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yezt
 * @description
 * @date 2021/12/21 9:21
 */
@Service
public class AwaitUserService {
    private static final Logger log = LoggerFactory.getLogger(AwaitUserService.class);

    @Autowired
    private RemoteUserService remoteUserService;

    /**
     * 查询当前用户信息
     *
     * @return 用户基本信息
     */
    public UserInfo info() {
        String username = SecurityUtils.getUsername();
        R<UserInfo> userResult = remoteUserService.getUserInfo(username);
        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData()))
        {
            log.info("数据权限范围查询用户：{} 不存在.", username);
            return null;
        }
        return userResult.getData();
    }
}
