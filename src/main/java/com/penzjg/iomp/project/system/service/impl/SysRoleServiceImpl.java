package com.penzjg.iomp.project.system.service.impl;

import com.penzjg.iomp.common.utils.StringUtils;
import com.penzjg.iomp.project.system.domain.SysRole;
import com.penzjg.iomp.project.system.mapper.SysRoleMapper;
import com.penzjg.iomp.project.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author : 江上渔者
 * @DATE : 2020-06-30 21:25
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        List<SysRole> perms = sysRoleMapper.selectRolePermissionByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms) {
            if(StringUtils.isNotNull(perm)){
                // role_key中可能有多个角色用","隔开,如"common,user",所以要进行split
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }
}
