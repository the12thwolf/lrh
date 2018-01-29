package com.ww.mvc.controller;

import com.ww.component.DataComponent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/4.
 */
@Controller
@RequestMapping("/personal")
public class PersonalController {
    @Resource
    private DataComponent dataComponent;
    private final Logger logger = Logger.getLogger(TestController.class);
    @Autowired
    protected HttpSession session;
    @Autowired
    private HttpServletRequest request;

    //支出项目数据浏览初始页面
    @RequestMapping(value = "/personalInformation")
    public ModelAndView personalInformation() {

        ModelAndView view = dataComponent.personalInformation();
        return view;
    }

    @RequestMapping(value = "/uploadPersonalPhoto")
    public ModelAndView uploadPersonalPhoto(HttpServletRequest request) {
        try {
            ModelAndView view = dataComponent.uploadPersonalPhoto(request);
            return view;
        } catch (Exception e) {
            return new ModelAndView("fail");
        }

    }


}
