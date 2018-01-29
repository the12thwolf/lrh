package com.ww.component;

import com.ww.domain.User;
import com.ww.exception.MyDataException;
import com.ww.service.RedisService;
import com.ww.service.UserService;
import com.ww.util.DataUtil;
import com.ww.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/20.
 */
@Service
public class UserCompnent {
    private final Logger logger = Logger.getLogger(UserCompnent.class);
    @Resource
    private UserService userService;
    @Resource
    private RedisService redisService;
    public ModelAndView processLogin(HttpServletRequest request, HttpSession session) {
        ModelAndView view = new ModelAndView();
        String message="";
        String userPriv="";
        //返回消息
        String userName = (String)request.getParameter("userName");
        logger.info("userName="+userName);
        //获取用户输入的用户名
        String passwordInput = (String)request.getParameter("password");

        if(StringUtil.stringIsBlank(userName)||StringUtil.stringIsBlank(passwordInput)){
            view=nameOrPasswdWrong();
            //返回登录页面
            return view;
        }
        User user = userService.getUserByUserName(userName);
        try {
            /**
             * 这里有个问题，mybatis插入数据的时候，md5加密的数据总是不能完整插入，正在看是什么原因
             */
            if(!DataUtil.passwdIsRight(user.getPassword(),passwordInput)){
                view=nameOrPasswdWrong();
                //返回登录页面
                return view;
            }
        } catch (Exception e) {
            view=nameOrPasswdWrong();
            //返回登录页面
            return view;
        }
        //获取用户权限
        userPriv=getPriv(user.getRolPriv());
        String tokenValue=DataUtil.geToken();
        String tokenName=user.getUserName()+"Token";
        //随机生成一个 token
        view.setViewName(userPriv);

        session.setAttribute("user",user);
        session.setAttribute(tokenName,tokenValue);
        redisService.setStringValue(tokenName,tokenValue);
        logger.debug("把用户token存入jdies:"+tokenValue);
        //将 token 存入jedis保存,token的名字就是userName+"Token"
        //登录后将user存入session
        return view;
    }

    private String getPriv(String rolPrivIn) {
        int rolPriv = Integer.valueOf(rolPrivIn);
        String res="";
        /**
         * 获取到用户的角色 根据角色不同转发到不同的页面上
         */
        switch(rolPriv){
            case 1:
                res = "userComm";//1-普通管理员
                break;
            case 2:
                res = "userSysAdm";//2-系统管理员
                break;
            case 3:
                res = "userBusiness";//3-业务管理员
                break;
            case 4:
                res = "userSup";//4-超级管理员
                break;
        }
        return  res;
    }

    private ModelAndView nameOrPasswdWrong() {
        String message="用户名或者密码错误";
        ModelAndView view = new ModelAndView();
        view.addObject("message",message);
        view.setViewName("login");
        return view;
    }

    public ModelAndView processRegit(User user) {
        ModelAndView view = new ModelAndView();
        String message="";
        User userExist = userService.getUserByUserName(user.getUserName());
        Map<String,Object> data = new HashMap<String,Object>();
        if(userExist !=null){
            //用户名重复的还是返回注册页面
            message = "您刚才输入的用户名 \"" +user.getUserName()+ "\" 太抢手了,请重新注册";
            return toRegitPage(message);
        }
        user.setRolPriv("1");//自行注册的用户 默认权限都是1
        //将密码加密
        try {
            String passwordEnc=DataUtil.encryptionMD5(user.getPassword());
            user.setPassword(passwordEnc);
            logger.info("注册后的密码:"+passwordEnc);
            userService.insertSelective(user);
            //注册成功
            message = "注册成功, 您的用户名是, "+user.getUserName()+", 请登录";
            view.addObject("message",message);
            view.setViewName("login");
            //跳转到登录页面
            return view;
        } catch (MyDataException e) {
            message = "内部错误，请联系管理员";
            return toRegitPage(message);
        } catch (NoSuchAlgorithmException e) {
            message = "内部错误，请联系管理员";
            return toRegitPage(message);
        }
    }

    private ModelAndView toRegitPage(String message) {
        ModelAndView view = new ModelAndView("regist");
        view.addObject("message",message);
        return view;
    }

    public Map<String,Object> userExists(String userName) {
        String checkUserNameExistMessage="";
        User userExist = userService.getUserByUserName(userName);
        Map<String,Object> data = new HashMap<String,Object>();
        if(userExist !=null){
            checkUserNameExistMessage = "您刚才输入的用户名 \"" +userName+ "\" 太抢手了  请重新注册";
        }else{
            checkUserNameExistMessage = "用户名可用";
        }
        data.put("checkUserNameExistMessage", checkUserNameExistMessage);
        return data;
    }

    public ModelAndView user_backIndex(HttpSession session) {
        ModelAndView view = new ModelAndView();
        User user=(User)session.getAttribute("user");
        //获取用户权限
        String userPriv=getPriv(user.getRolPriv());
        view.setViewName(userPriv);
        return view;
    }
}
