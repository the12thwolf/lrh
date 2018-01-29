package com.ww.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/11/27.
 */
@Controller

public class MainController {
    @Autowired
    protected HttpSession session;
    @Autowired
    private HttpServletRequest request;


    @RequestMapping(value = "*")
    public ModelAndView notFound(){
        return new  ModelAndView("404");
    }
}
