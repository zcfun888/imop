package com.penzjg.iomp.project.system.mapper;

import java.util.List;

/**
 * 菜单表 数据层
 *
 * @Author : 江上渔者
 * @DATE : 2020-06-30 22:32
 */
public interface SysMenuMapper {

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    List<String> selectMenuPermsByUserId(Long userId);
}
