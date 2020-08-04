package com.penzjg.iomp.common.exception.user;

/**
 * 验证码错误异常类
 *
 * @Author : 江上渔者
 * @DATE : 2020-07-04 19:10
 */
public class CaptchaException extends UserException{

    private static final long serialVersionUID = 1L;

    public CaptchaException(){
        super("user.jcaptcha.error", null);
    }
}
