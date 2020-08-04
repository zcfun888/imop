package com.penzjg.iomp.framework.security;

import lombok.Data;

/**
 * 用户登录对象
 *
 * @Author : 江上渔者
 * @DATE : 2020-07-01 19:38
 */
@Data
public class LoginBody {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid = "";

}
