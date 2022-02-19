package com.ake.common.security.service;

import com.ake.nbems.common.core.constant.CacheConstants;
import com.ake.nbems.common.core.constant.SecurityConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @author yezt
 * @description 重写原生方法支持redis缓存
 * @date 2021/12/14 16:01
 */
public class RedisClientDetailsService extends JdbcClientDetailsService {
    public RedisClientDetailsService(DataSource dataSource) {
        super(dataSource);
        super.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
        super.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
    }

    @Override
    @Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) {
        return super.loadClientByClientId(clientId);
    }
}
