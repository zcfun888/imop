package com.penzjg.iomp.common.utils;


import java.util.UUID;

/**
 * ID生成器工具类
 *
 * @Author : 江上渔者
 * @DATE : 2020-07-03 21:06
 */
public class IdUtils {

    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
