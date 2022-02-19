package com.ake.nbems.auth.config;

import com.ake.common.security.domain.LoginUser;
import com.ake.nbems.common.core.constant.SecurityConstants;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yezt
 * @description 自定义生成令牌
 * @date 2021/12/15 17:26
 */
@Component
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (authentication.getUserAuthentication() != null) {
            Map<String, Object> additionalInformation = new LinkedHashMap<>();
            LoginUser user = (LoginUser) authentication.getUserAuthentication().getPrincipal();
            additionalInformation.put(SecurityConstants.DETAILS_USER_ID, user.getUserId());
            additionalInformation.put(SecurityConstants.DETAILS_USERNAME, user.getUsername());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
        }
        return accessToken;
    }
}
