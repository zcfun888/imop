package com.penzjg.iomp.project.system.service;

import java.util.Set;

/**
 * 菜单 业务层
 *
 * @Author : 江上渔者
 * @DATE : 2020-06-30 22:29
 */
public interface ISysMenuService {

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectMenuPermsByUserId(Long userId);
}
