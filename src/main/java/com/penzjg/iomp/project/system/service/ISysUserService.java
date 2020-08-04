package com.penzjg.iomp.project.system.service;

import com.penzjg.iomp.project.system.domain.SysUser;

/**
 * @Author : 江上渔者
 * @DATE : 2020-06-30 20:56
 */
public interface ISysUserService {

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    SysUser selectUserById(Long userId);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUser selectUserByUsername(String userName);
}
