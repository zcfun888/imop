package com.penzjg.iomp.project.system.service.impl;

import com.penzjg.iomp.project.system.domain.SysUser;
import com.penzjg.iomp.project.system.mapper.SysUserMapper;
import com.penzjg.iomp.project.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : 江上渔者
 * @DATE : 2020-06-30 20:59
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long userId) {
        return sysUserMapper.selectUserById(userId);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return
     */
    @Override
    public SysUser selectUserByUsername(String userName) {
        return sysUserMapper.selectUserByUsername(userName);
    }
}
