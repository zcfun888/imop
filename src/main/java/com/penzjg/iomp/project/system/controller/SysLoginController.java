package com.penzjg.iomp.project.system.controller;

import com.penzjg.iomp.common.constant.Constants;
import com.penzjg.iomp.common.exception.BaseException;
import com.penzjg.iomp.common.exception.user.UserPasswordNotMatchException;
import com.penzjg.iomp.common.utils.ServletUtils;
import com.penzjg.iomp.framework.redis.RedisCache;
import com.penzjg.iomp.framework.security.LoginBody;
import com.penzjg.iomp.framework.security.LoginUser;
import com.penzjg.iomp.framework.security.service.SysLoginService;
import com.penzjg.iomp.framework.security.service.SysPermissionService;
import com.penzjg.iomp.framework.security.service.TokenService;
import com.penzjg.iomp.framework.web.domain.AjaxResult;
import com.penzjg.iomp.project.system.domain.SysLogininfor;
import com.penzjg.iomp.project.system.domain.SysUser;
import com.penzjg.iomp.project.system.service.ISysLogininforService;
import com.penzjg.iomp.project.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 登录验证
 *
 * @Author : 江上渔者
 * @DATE : 2020-06-30 20:35
 */
@RestController
@Slf4j
public class SysLoginController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SysLoginService sysLoginService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisCache redisCache;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody){
        AjaxResult ajax = AjaxResult.success();
        //生成令牌
        String token = sysLoginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                      loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getInfor")
    public AjaxResult getInfo(){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        //角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        //权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    @PreAuthorize("@ss.hasRole('admin')")
    @GetMapping("/getUser")
    public AjaxResult getUser(){
        AjaxResult ajax = AjaxResult.success();
        SysUser user = sysUserService.selectUserById(1L);
        ajax.put("user", user);
        return ajax;
    }

    @GetMapping("/createLoginUser")
    public LoginUser createLoginUser(){
        SysUser user = sysUserService.selectUserById(1L);
        LoginUser loginUser = new LoginUser(user, permissionService.getMenuPermission(user));
        return loginUser;
    }

    @GetMapping("/refresh")
    public void refreshToken(){
        SysUser user = sysUserService.selectUserById(1L);
        LoginUser loginUser = new LoginUser(user, permissionService.getMenuPermission(user));
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + 30 * 60 *1000);
        String userKey = "login_tokens:1c871f04-ac02-40ef-9ae8-e4cfe8c84767";
        redisCache.setCacheObject(userKey,loginUser,30, TimeUnit.MINUTES);
    }
}
