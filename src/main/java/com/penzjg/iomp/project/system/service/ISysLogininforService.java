package com.penzjg.iomp.project.system.service;

import com.penzjg.iomp.project.system.domain.SysLogininfor;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层
 *
 * @Author : 江上渔者
 * @DATE : 2020-07-03 19:26
 */
public interface ISysLogininforService {

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    void insertLogininfor(SysLogininfor logininfor);

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

    /**
     * 清空系统登录日志
     */
    void cleanLogininfor();
}
