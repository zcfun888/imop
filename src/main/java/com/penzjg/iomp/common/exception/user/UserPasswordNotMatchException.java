package com.penzjg.iomp.common.exception.user;

/**
 * 用户密码不正确或不符合规范异常类
 *
 * @Author : 江上渔者
 * @DATE : 2020-07-04 19:14
 */
public class UserPasswordNotMatchException extends UserException{

    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException(){
        super("user.password.not.match", null);
    }
}
