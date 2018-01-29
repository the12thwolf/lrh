package com.ww.interceptors;

import com.ww.domain.User;
import com.ww.mvc.controller.TestController;
import com.ww.service.RedisService;
import com.ww.util.NetUtil;
import com.ww.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/11/17.
 */
public class AccessInterceptor implements HandlerInterceptor {
    private final Logger logger = Logger.getLogger(AccessInterceptor.class);
    @Resource
    private RedisService redisService;
    @Value("${pro.model}")
    private String proModel;
    //生产，测试模式
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object o)
            throws Exception {
        logger.info("拦截器运行。。。,访问的地址:"+request.getRequestURL().toString()+","+request.getRequestURI());

        HttpSession session = request.getSession();
        ServletContext application = session.getServletContext();
        User user=(User)session.getAttribute("user");
        //获取到user后也可以再验证 token 数据，如果token数据和redis记录的匹配就验证通过，否则重新登录
        if(user==null|| StringUtil.stringIsBlank(user.getUserName())){
            //未登录
            if(NetUtil.isAjaxRequest(request)){
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");
            }else{
                //直接跳转到登录页面
                String url = request.getContextPath()+ "/login";
                response.sendRedirect(url);
            }
            return false;
        }else{    //已经登录
            String tokenName=user.getUserName()+"Token";
            String tokenValueService=redisService.getStringValueRedisTemplate(tokenName);
            String tokenValueClient=(String)session.getAttribute(tokenName);
            if(StringUtil.stringIsNotBlank(tokenValueClient) && StringUtil.stringIsNotBlank(tokenValueClient)
                    &&tokenValueService.equals(tokenValueClient)){
                return true;
            }else{
                //直接跳转到登录页面
                String url = request.getContextPath()+ "/login";
                response.sendRedirect(url);
                return false;
            }

        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
