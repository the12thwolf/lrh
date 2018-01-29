package com.ww.mvc.controller;

import com.ww.domain.User;
import com.ww.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created by Administrator on 2017/11/3.
 */
@Controller
public class TestController {
    @Autowired
    protected HttpSession session;
    @Autowired
    private HttpServletRequest request;
    @Resource
    private UserService userService;
    private final Logger logger = Logger.getLogger(TestController.class);

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView test() {
        logger.info("访问test");


        ModelAndView view = new ModelAndView("timeTest");

        return view;
    }


}
