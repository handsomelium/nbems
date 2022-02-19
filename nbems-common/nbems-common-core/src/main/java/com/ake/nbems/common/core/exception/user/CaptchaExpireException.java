package com.ake.nbems.common.core.exception.user;

/**
 * 验证码失效异常类
 * 
 * @author lium
 */
public class CaptchaExpireException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaExpireException()
    {
        super("user.jcaptcha.expire", null);
    }
}
