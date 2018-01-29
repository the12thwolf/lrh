package com.ww.mvc.controller;

/**
 * Created by Administrator on 2017/11/20.
 */

import com.ww.component.UserCompnent;
import com.ww.domain.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
public class UserController {
    private final Logger logger = Logger.getLogger(UserController.class);
    @Resource
    private UserCompnent userCompnent;
    @Autowired
    protected HttpSession session;
    @Autowired
    private HttpServletRequest request;
    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView toLogin() {
        ModelAndView view = new ModelAndView("login");
        //转到login登录页面
        return view;
    }

    /**
     * 处理登录事件
     * @return
     */
    @RequestMapping(value = "/user_login", method = RequestMethod.POST)
    public ModelAndView login() {
        ModelAndView view = userCompnent.processLogin(request,session);
        return view;
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping(value = "/user_toRegist", method = RequestMethod.GET)
    public ModelAndView user_toRegist() {
        ModelAndView view = new ModelAndView("regist");
        //转到login登录页面
        return view;
    }

    //处理注册事件
    @RequestMapping(value = "/user_regist", method = RequestMethod.POST)
    public ModelAndView user_regist(@ModelAttribute("user") User user) {
        ModelAndView view = userCompnent.processRegit(user);
        //调用注册处理程序
        return view;
    }

    @RequestMapping(value = "/user_backIndex", method = RequestMethod.GET)
    public ModelAndView user_backIndex() {
        ModelAndView view = userCompnent.user_backIndex(session);
        //调用返回主页程序
        return view;
    }


}
