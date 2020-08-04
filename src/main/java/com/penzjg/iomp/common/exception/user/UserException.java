package com.penzjg.iomp.common.exception.user;

import com.penzjg.iomp.common.exception.BaseException;

/**
 * 用户信息异常类
 *
 * @Author : 江上渔者
 * @DATE : 2020-07-03 20:17
 */
public class UserException extends BaseException {

    private static final long serialVersionUID =1L;

    public UserException(String code, Object[] args){
        super("user", code, args, null);
    }
}
