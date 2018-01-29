package com.ww.mvc.controller;

import com.ww.component.DataComponent;
import com.ww.domain.PayItem;
import com.ww.domain.PaySubitem;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/11/21.
 */
@Controller
@RequestMapping(value = "/dataManage")
public class DataManageController {
    @Autowired
    protected HttpSession session;
    @Autowired
    private HttpServletRequest request;
    @Resource
    private DataComponent dataComponent;
    private final Logger logger = Logger.getLogger(TestController.class);

    @RequestMapping(value = "/data_dataList", method = RequestMethod.GET)
    public ModelAndView data_dataList() {

        ModelAndView view = dataComponent.data_dataList(request);
        return view;
    }

    //支出项目数据浏览初始页面
    @RequestMapping(value = "/payItemList", method = RequestMethod.GET)
    public ModelAndView payItemList() {

        ModelAndView view = dataComponent.payItemList(request);
        return view;
    }

    //支出项目数据浏览初始页面
    @RequestMapping(value = "/paySubitemList")
    public ModelAndView paySubitemList() {

        ModelAndView view = dataComponent.paySubitemList(request);
        return view;
    }


    //转到增加支出项目的页面
    @RequestMapping(value = "/toPaySubitemList", method = RequestMethod.GET)
    public ModelAndView toPaySubitemList() {
        try {
            ModelAndView view = dataComponent.toPaySubitemList(request);
            return view;
        } catch (Exception e) {
            return new ModelAndView("fail");
        }
    }




    //转到增加支出项目的页面
    @RequestMapping(value = "/toPayItemAdd", method = RequestMethod.GET)
    public ModelAndView toPayItemAdd() {
        try {
            ModelAndView view = dataComponent.toPayItemAdd(request);
            return view;
        } catch (UnsupportedEncodingException e) {
            return new ModelAndView("fail");
        }
    }
    @RequestMapping(value = "/toPaySubitemAdd", method = RequestMethod.GET)
    public ModelAndView toPaySubitemAdd(@RequestParam("itemNameOld") String itemNameOld) {
        try {
            ModelAndView view = dataComponent.toPaySubitemAdd(itemNameOld,request);
            return view;
        } catch (UnsupportedEncodingException e) {
            return new ModelAndView("fail");
        }
    }

    //处理增加操作
    @RequestMapping(value = "/addPayItem", method = RequestMethod.POST)
    public ModelAndView addPayItem(@ModelAttribute("payItem") PayItem payItem) {
        try {
            ModelAndView view = dataComponent.addPayItem(payItem);
            return view;
        } catch (UnsupportedEncodingException e) {
            return new ModelAndView("fail");
        }

    }


    @RequestMapping(value = "/addPaySubitem", method = RequestMethod.POST)
    public ModelAndView addPaySubitem(@ModelAttribute("paySubitem") PaySubitem paySubitem,
                                      @RequestParam("itemNameOld") String itemNameOld) {
        try {
            ModelAndView view = dataComponent.addPaySubitem(paySubitem,itemNameOld);
            return view;
        } catch (UnsupportedEncodingException e) {
            return new ModelAndView("fail");
        }

    }



    @RequestMapping(value = "/deletePayItem", method = RequestMethod.GET)
    public ModelAndView deletePayItem(@RequestParam("itemId") Long itemId) {

        ModelAndView view = dataComponent.deletePayItem(itemId);
        return view;
    }

    @RequestMapping(value = "/deletePaySubitem", method = RequestMethod.GET)
    public ModelAndView deletePaySubitem(@RequestParam("subitemId") Long subitemId,@RequestParam("itemName") String itemName) {

        ModelAndView view = dataComponent.deletePaySubitem(subitemId,itemName);
        return view;
    }


    @RequestMapping(value = "/toPayItemModify", method = RequestMethod.GET)
    public ModelAndView toPayItemModify(@RequestParam("itemId") Long itemId) {
        try {
            return dataComponent.toPayItemModify(itemId,request);
        } catch (UnsupportedEncodingException e) {
            return new ModelAndView("fail");
        }

    }

    @RequestMapping(value = "/toPaySunitemModify", method = RequestMethod.GET)
    public ModelAndView toPaySunitemModify(@RequestParam("subitemId") Long subitemId,@RequestParam("itemName") String itemName) {

        try {
            return dataComponent.toPaySunitemModify(subitemId,itemName,request);
        } catch (UnsupportedEncodingException e) {
            return new ModelAndView("fail");
        }

    }

    @RequestMapping(value = "/payItemModify", method = RequestMethod.POST)
    public ModelAndView payItemModify(@ModelAttribute("payItem") PayItem payItem) {

        try {
            ModelAndView view = dataComponent.payItemModify(payItem);
            return view;
        } catch (UnsupportedEncodingException e) {
            return new ModelAndView("fail");
        }
    }

    @RequestMapping(value = "/paySubitemModify", method = RequestMethod.POST)
    public ModelAndView paySubitemModify(@ModelAttribute("paySubitem") PaySubitem paySubitem) {

        try {
            ModelAndView view = dataComponent.paySubitemModify(paySubitem);
            return view;
        } catch (Exception e) {
            return new ModelAndView("fail");
        }
    }


}
