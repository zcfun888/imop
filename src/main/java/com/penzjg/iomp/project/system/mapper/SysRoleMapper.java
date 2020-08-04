package com.penzjg.iomp.project.system.mapper;

import com.penzjg.iomp.project.system.domain.SysRole;

import java.util.List;

/**
 * 角色表 数据层
 *
 * @Author : 江上渔者
 * @DATE : 2020-06-30 21:27
 */
public interface SysRoleMapper {

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<SysRole> selectRolePermissionByUserId(Long userId);
}
