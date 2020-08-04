package com.penzjg.iomp.common.exception.user;



/**
 * 验证码失效异常类
 *
 * @Author : 江上渔者
 * @DATE : 2020-07-03 20:05
 */
public class CaptchaExpireException extends UserException {

    private static final long serialVersionUID = 1L;

    public CaptchaExpireException(){
        super("user.jcaptcha.expire",null);
    }
}
