package com.penzjg.iomp.common.utils;

import cn.hutool.core.text.StrFormatter;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 字符串工具类
 *
 * @Author : 江上渔者
 * @DATE : 2020-06-30 19:40
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /** 空字符串 */
    private static final String NULLSTR = "";

    /** 下划线 */
    private static final char SEPARATOR = '_';

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object)
    {
        return object == null;
    }

    public static boolean isNotNull(Object object){
        return !isNull(object);
    }

    /**
     * * 判断一个对象数组是否为空
     *
     * @param objects 要判断的对象数组
     ** @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects){
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str){
        return isNull(str);
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    public static String format(String template, Object... params){
        if (isEmpty(params) || isEmpty(template)){
            return template;
        }
        return StrFormatter.format(template,params);
    }
}
