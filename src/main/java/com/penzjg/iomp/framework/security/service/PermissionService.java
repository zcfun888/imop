package com.penzjg.iomp.framework.security.service;

import com.penzjg.iomp.common.utils.ServletUtils;
import com.penzjg.iomp.common.utils.StringUtils;
import com.penzjg.iomp.framework.security.LoginUser;
import com.penzjg.iomp.project.system.domain.SysRole;
import com.penzjg.iomp.project.system.service.ISysMenuService;
import com.penzjg.iomp.project.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 自定义权限实现，ss取自Spring Security首字母
 *
 * @Author : 江上渔者
 * @DATE : 2020-07-12 21:24
 */
@Service("ss")
public class PermissionService {

    /** 所有权限标识 */
    private static final String ALL_PERMISSION = "*.*.*";

    /** 管理员角色权限标识 */
    private static final String SUPER_ADMIN = "admin";

    private static final String ROLE_DELIMETER = ",";

    private static final String PERMISSION_DELIMETER = ",";

    @Autowired
    private TokenService tokenService;

    /**
     * 判断用户是否拥有某个角色
     *
     * @param role 角色字符串
     * @return 用户是否具备某角色
     */
    public boolean hasRole(String role){
        if (StringUtils.isEmpty(role)){
            return false;
        }
        //获取登录用户
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        //如果登录用户为空或者角色为空则返回false
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getUser().getRoles())){
            return false;
        }
        for (SysRole sysRole : loginUser.getUser().getRoles()){
            String roleKey = sysRole.getRoleKey();
            //如果是管理员角色就返回true
            if (SUPER_ADMIN.contains(roleKey) || roleKey.contains(StringUtils.trim(role))){
                return true;
            }
        }
        return false;
    }


}
