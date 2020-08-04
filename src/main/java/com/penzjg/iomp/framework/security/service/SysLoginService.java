package com.penzjg.iomp.framework.security.service;

import com.penzjg.iomp.common.constant.Constants;
import com.penzjg.iomp.common.exception.CustomException;
import com.penzjg.iomp.common.exception.user.CaptchaException;
import com.penzjg.iomp.common.exception.user.CaptchaExpireException;
import com.penzjg.iomp.common.utils.MessageUtils;
import com.penzjg.iomp.framework.manager.AsyncManager;
import com.penzjg.iomp.framework.manager.factory.AsyncFactory;
import com.penzjg.iomp.framework.redis.RedisCache;
import com.penzjg.iomp.framework.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录校验方法
 *
 * @Author : 江上渔者
 * @DATE : 2020-07-01 20:01
 */
@Component
public class SysLoginService {

    @Autowired
    private RedisCache redisCache;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param captcha 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid){

        //获取验证码的key值,如:"captcha_codes:57d4e68669e14ae9a7e3196c5d3c95b1"
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        //根据验证码的key值获取到验证码
        String captcha = redisCache.getCacheObject(verifyKey);
        //获取到验证码后在redis中删除这个验证码
        redisCache.deleteObject(verifyKey);
        //如果验证码为空（失效）,通过异步调用（多线程）插入一条登录失败的日志信息到登录日志数据表中,并抛出验证码失效异常
        if (captcha == null){
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        //如果验证码不匹配（忽略大小写）,通过异步调用（多线程）插入一条登录失败的日志信息到登录日志数据表中,并抛出验证码错误异常
        if (!code.equalsIgnoreCase(captcha)){
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
        //用户验证
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(authenticationToken);
        }catch (Exception e){
            //用户或密码错误时抛出异常
            if (e instanceof BadCredentialsException){
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            }else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //生成token
        String token = tokenService.createToken(loginUser);
        return token;
    }
}
