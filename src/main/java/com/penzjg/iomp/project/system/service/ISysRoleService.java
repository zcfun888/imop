package com.penzjg.iomp.project.system.service;

import java.util.Set;

/**
 * 角色业务层
 *
 * @Author : 江上渔者
 * @DATE : 2020-06-30 21:24
 */
public interface ISysRoleService {

    Set<String> selectRolePermissionByUserId(Long userId);
}
