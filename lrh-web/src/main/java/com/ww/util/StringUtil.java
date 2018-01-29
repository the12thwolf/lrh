package com.ww.util;

/**
 * Created by Administrator on 2017/9/24.
 */
public class StringUtil {
    public static boolean stringIsBlank(String value) {
        return (null==value || "".equals(value.trim()));
    }

    public static boolean stringIsNotBlank(String value) {
        return !stringIsBlank(value);
    }
}
