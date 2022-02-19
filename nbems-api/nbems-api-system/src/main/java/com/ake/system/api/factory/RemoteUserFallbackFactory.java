package com.ake.system.api.factory;

import com.ake.nbems.common.core.domain.R;
import com.ake.system.api.domain.SysUser;
import com.ake.system.api.model.UserInfo;
import com.ake.system.api.service.RemoteUserService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户服务降级处理
 * 
 * @author guojm
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService()
        {
            @Override
            public R<UserInfo> getUserInfo(String username)
            {
                return null;
            }

//			@Override
//			public R<WxOAuth2UserInfo> wxUserInfo(String code) throws WxErrorException {
//				return null;
//			}

			@Override
			public R<SysUser> getUserInfo() {
				return null;
			}

			@Override
			public R<List<Long>> checkedArea() {
				return null;
			}

//			@Override
//			public R<KfxtSysUser> getKfxtUserInfo(String username) {
//				return null;
//			}
        };
    }
}
