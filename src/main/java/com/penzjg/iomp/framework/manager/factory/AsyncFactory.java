package com.penzjg.iomp.framework.manager.factory;

import com.penzjg.iomp.common.constant.Constants;
import com.penzjg.iomp.common.utils.spring.SpringUtils;
import com.penzjg.iomp.project.system.domain.SysLogininfor;
import com.penzjg.iomp.project.system.service.ISysLogininforService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**异步工厂（产生任务用）
 *
 * @Author : 江上渔者
 * @DATE : 2020-07-03 18:43
 */
public class AsyncFactory {

    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 记录登陆信息
     *
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message,
                   final Object... args)
    {
        //final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        //final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {

                // 封装登录日志对象
                SysLogininfor logininfor = new SysLogininfor();
                logininfor.setUserName(username);
                logininfor.setMsg(message);
                //日志状态 如果登录成功或注销则状态为"0",否则登录失败状态为"1"
                if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)){
                    logininfor.setStatus(Constants.SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(status)){
                    logininfor.setStatus(Constants.FAIL);
                }

                // 插入登录日志数据
                SpringUtils.getBean(ISysLogininforService.class).insertLogininfor(logininfor);

            }
        };
    }
}
