package com.ake.nbems.auth.controller;

import com.ake.nbems.common.core.constant.SecurityConstants;
import com.ake.nbems.common.core.domain.R;
import com.ake.nbems.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yezt
 * @description
 * @date 2021/12/15 16:48
 */
@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private TokenStore tokenStore;

    @DeleteMapping("/logout")
    @ResponseBody
    public R<?> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
        if (StringUtils.isEmpty(authHeader)) {
            return R.ok();
        }

        String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, StringUtils.EMPTY).trim();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        if (accessToken == null || StringUtils.isEmpty(accessToken.getValue())) {
            return R.ok();
        }

        // 清空 access token
        tokenStore.removeAccessToken(accessToken);

        // 清空 refresh token
        OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
        tokenStore.removeRefreshToken(refreshToken);
        Map<String, ?> map = accessToken.getAdditionalInformation();
        if (map.containsKey(SecurityConstants.DETAILS_USERNAME)) {
            String username = (String) map.get(SecurityConstants.DETAILS_USERNAME);
        }
        return R.ok();
    }
}
