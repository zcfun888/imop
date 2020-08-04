package com.penzjg.iomp;

import com.penzjg.iomp.common.utils.MessageUtils;
import com.penzjg.iomp.common.utils.SecurityUtils;
import com.penzjg.iomp.framework.redis.RedisCache;
import com.penzjg.iomp.framework.security.LoginUser;
import com.penzjg.iomp.framework.security.service.SysPermissionService;
import com.penzjg.iomp.project.system.domain.SysUser;
import com.penzjg.iomp.project.system.mapper.SysUserMapper;
import com.penzjg.iomp.project.system.service.ISysRoleService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.security.Key;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

@SpringBootTest
class IompApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    ISysRoleService roleService;

    @Autowired
    SysPermissionService permissionService;

    @Autowired
    RedisCache redisCache;

    private static final String strKey = "Zl+7iQh6ftixY7P2S1Nll4Ty3CR2TDGsyOsNYf9oRT0=";

    @Test
    void contextLoads() throws SQLException {

        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    void testUser(){
        SysUser sysUser = sysUserMapper.selectUserById(1L);

        System.out.println(SecurityUtils.matchesPassword("admin123",sysUser.getPassword()));
    }

    @Test
    void testRole(){
        Set<String> permsSet = roleService.selectRolePermissionByUserId(2L);
        System.out.println(permsSet);
    }

    @Test
    void i18nTest(){
        System.out.println(MessageUtils.message("user.jcaptcha.expire"));;
    }

    @Test
    void selectByUsername(){
        SysUser user = sysUserMapper.selectUserByUsername("admin");
        System.out.println(user);
    }

    @Test
    void generateToken(){
        Key key = Keys.hmacShaKeyFor(strKey.getBytes());
        String token = Jwts.builder().setSubject("zhangch")
                .setIssuedAt(new Date())
                .setIssuer("www.penzjg.com")
                .signWith(key).compact();
        System.out.println(token);

        String sub = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
                .getBody().getSubject();
        System.out.println(sub);
    }

    @Test
    void encodeTest(){
        String passwd = "admin123";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(passwd);
        System.out.println(encode);
    }

    @Test
    void getLoginUser(){
        String userKey = "login_tokens:1c032cba-5485-4b50-abd2-c2682413e31f";
        LoginUser user = redisCache.getCacheObject(userKey);
        System.out.println(user);
    }

    @Test
    void createLoginUser(){
        SysUser user = sysUserMapper.selectUserById(1L);
        LoginUser loginUser = new LoginUser(user,permissionService.getMenuPermission(user));
        System.out.println(loginUser);
    }

}
