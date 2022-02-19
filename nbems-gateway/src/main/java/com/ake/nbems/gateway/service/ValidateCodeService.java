package com.ake.nbems.gateway.service;


import com.ake.nbems.common.core.exception.CaptchaException;
import com.ake.nbems.common.core.web.domain.AjaxResult;

import java.io.IOException;

/**
 * 验证码处理
 * 
 * @author guojm
 */
public interface ValidateCodeService {
    /**
     * 生成验证码
     */
    public AjaxResult createCapcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     */
    public void checkCapcha(String key, String value) throws CaptchaException;
}
