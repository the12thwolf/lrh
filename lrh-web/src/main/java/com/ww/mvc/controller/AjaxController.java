package com.ww.mvc.controller;

import com.ww.component.DataComponent;
import com.ww.component.UserCompnent;
import com.ww.domain.PayItem;
import com.ww.domain.PaySubitem;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/15.
 */
@Controller
@RequestMapping("/ajax")
public class AjaxController {
    private final Logger logger = Logger.getLogger(AjaxController.class);
    @Resource
    private UserCompnent userCompnent;
    @Autowired
    protected HttpSession session;
    @Autowired
    private HttpServletRequest request;
    @Resource
    private DataComponent dataComponent;


    @RequestMapping(value="/ajaxTest",method= RequestMethod.POST)
    public @ResponseBody Map<String,Object> ajaxTest(String name, String password)
    {

        Map<String,Object> data=new HashMap<String,Object>();
        data.put("msg", "我接到ajax请求了！！！");
        System.out.println("name:"+name+",password:"+password);
        return data;
    }

    @RequestMapping(value="/checkUserNameExist",method= RequestMethod.POST)
    public @ResponseBody Map<String,Object> checkUserNameExist(
            @RequestParam("userName")String userName){

        Map<String,Object> data=userCompnent.userExists(userName);

        return data;
    }

    @RequestMapping(value="/checkItemNameExist",method= RequestMethod.POST)
    public @ResponseBody Map<String,Object> checkItemNameExist(
            @RequestParam("itemName")String itemName){
        Map<String,Object> data=dataComponent.itemNameExists(itemName);
        return data;
    }

    @RequestMapping(value="/payItemList",method= RequestMethod.POST)
    public @ResponseBody List<PayItem> payItemList(){
        List<PayItem> payItemList = dataComponent.payItemListAll();
        return payItemList;
    }

    @RequestMapping(value="/getPaySubitemsByItemName",method= RequestMethod.POST)
    public @ResponseBody List<PaySubitem> getPaySubitemsByItemName(){
        return dataComponent.getPaySubitemsByItemName(request);
    }


    @RequestMapping(value="/payPersonList",method= RequestMethod.POST)
    public @ResponseBody List<PaySubitem> payPersonList(){
        //@RequestParam("itemName")String itemName
        return dataComponent.selectPayPersonListAll(request);
    }



    @RequestMapping(value="/checkPaySubitemExist",method= RequestMethod.POST)
    public @ResponseBody Map<String,Object> checkPaySubitemExist(
            @RequestParam("itemName")String itemName,@RequestParam("subitemName")String subitemName){
        logger.info("checkPaySubitemExist.....");
        Map<String,Object> data=dataComponent.paySubitemExists(itemName,subitemName);
        logger.info("返回"+data);
        return data;
    }

}
