package com.ww.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/11/20.
 */
public class NetUtil {

    /**
     * 判断是否为Ajax请求
     *
     * @param request
     *            HttpServletRequest
     * @return 是true, 否false
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }
}
