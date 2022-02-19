package com.ake.common.security.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 忽略服务间的认证
 *
 * @author guojm
 **/
@Component
@Configurable
@ConfigurationProperties(prefix = "security.oauth2.ignore")
public class AuthIgnoreConfig {
    private List<String> urls = new ArrayList<>();

    public List<String> getUrls()
    {
        return urls;
    }

    public void setUrls(List<String> urls)
    {
        this.urls = urls;
    }
}
