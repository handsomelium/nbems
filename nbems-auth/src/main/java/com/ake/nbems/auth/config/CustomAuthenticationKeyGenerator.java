package com.ake.nbems.auth.config;

import com.ake.nbems.common.core.utils.ServletUtils;
import com.ake.nbems.common.core.utils.StringUtils;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author yezt
 * @description 身份密钥生成器
 * @date 2021/12/15 17:10
 */
public class CustomAuthenticationKeyGenerator extends DefaultAuthenticationKeyGenerator {
    private static final String CLIENT_ID = "client_id";

    private static final String SCOPE = "scope";

    private static final String USERNAME = "username";

    private static final String USER_AGENT = "User-Agent";

    @Override
    public String extractKey(OAuth2Authentication authentication) {
        Map<String, String> values = new LinkedHashMap<String, String>();
        OAuth2Request authorizationRequest = authentication.getOAuth2Request();
        if (!authentication.isClientOnly()) {
            values.put(USERNAME, authentication.getName());
        }
        values.put(CLIENT_ID, authorizationRequest.getClientId());
        if (authorizationRequest.getScope() != null) {
            values.put(SCOPE, OAuth2Utils.formatParameterList(new TreeSet<String>(authorizationRequest.getScope())));
        }
        String userAgent = ServletUtils.getRequest().getHeader(USER_AGENT);
        if(StringUtils.isNotEmpty(userAgent)) {
            values.put(USER_AGENT, userAgent);
        }
        return generateKey(values);
    }
}
