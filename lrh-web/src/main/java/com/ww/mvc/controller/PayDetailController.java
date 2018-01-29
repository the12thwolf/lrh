package com.ww.mvc.controller;

import com.ww.component.DataComponent;
import com.ww.domain.PayDetail;
import com.ww.domain.PayDetailDataForPage;
import com.ww.domain.PayItem;
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
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/11/27.
 */
@Controller
@RequestMapping(value = "/payDetail")
public class PayDetailController {
    @Autowired
    protected HttpSession session;
    @Autowired
    private HttpServletRequest request;
    @Resource
    private DataComponent dataComponent;
    private final Logger logger = Logger.getLogger(PayDetailController.class);


    @RequestMapping(value = "/toPayDetailList")
    public ModelAndView toPayDetailList() {
        try {
            ModelAndView view = dataComponent.toPayDetailList(request);
            return view;
        } catch (Exception e) {
            return new ModelAndView("fail");
        }
    }

    @RequestMapping(value = "/toPayDetailAdd", method = RequestMethod.POST)
    public ModelAndView toPayDetailAdd() {
        try {
            ModelAndView view = dataComponent.toPayDetailAdd(request);
            return view;
        } catch (Exception e) {
            return new ModelAndView("fail");
        }
    }


    @RequestMapping(value = "/addPayItem", method = RequestMethod.POST)
    public ModelAndView addPayItem(@ModelAttribute("payItem") PayItem payItem) {
        try {
            ModelAndView view = dataComponent.addPayItem(payItem);
            return view;
        } catch (UnsupportedEncodingException e) {
            return new ModelAndView("fail");
        }

    }

    @RequestMapping(value = "/deletePayDetail")
    public ModelAndView deletePayDetail(@RequestParam("payId") long payId) {
        try {
            ModelAndView view = dataComponent.deletePayDetail(request,payId);
            return view;
        } catch (Exception e) {
            return new ModelAndView("fail");
        }

    }

    @RequestMapping(value = "/toModifyPayDetail")
    public ModelAndView toModifyPayDetail(@RequestParam("payId") long payId) {
        try {
            ModelAndView view = dataComponent.toModifyPayDetail(request,payId);
            return view;
        } catch (Exception e) {
            return new ModelAndView("fail");
        }

    }

    @RequestMapping(value = "/addPayDetail", method = RequestMethod.POST)
    public ModelAndView addPayDetail(@ModelAttribute("payDetail") PayDetail payDetail) {
        try {
            ModelAndView view = dataComponent.addPayDetail(payDetail,request);
            return view;
        } catch (Exception e) {
            return new ModelAndView("fail");
        }

    }

    @RequestMapping(value = "/modifyPayDetail", method = RequestMethod.POST)
    public ModelAndView modifyPayDetail(@ModelAttribute("payDetail") PayDetail payDetail) {
        try {
            ModelAndView view = dataComponent.modifyPayDetail(payDetail,request);
            return view;
        } catch (Exception e) {
            return new ModelAndView("fail");
        }

    }

}
