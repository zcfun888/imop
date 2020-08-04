package com.penzjg.iomp.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.penzjg.iomp.project.system.domain.SysUser;

/**
 * 用户表 数据层
 *
 * @Author : 江上渔者
 * @DATE : 2020-06-30 17:15
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectUserById(Long userId);

    SysUser selectUserByUsername(String userName);
}
