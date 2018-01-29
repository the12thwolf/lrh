package com.ww.mvc.controller;

import com.ww.dto.EmailSendDto;
import com.ww.service.ProducerService;
import com.ww.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * Created by Administrator on 2017/9/21.
 */
@Controller
public class MailController {
    @Resource(name="emailQueue")
    private Destination emailQueue;
    //队列消息生产者
    @Resource(name="producerService")
    private ProducerService producer;
    private final Logger logger = Logger.getLogger(MailController.class);


    @RequestMapping(value="/singlemailsend",method= RequestMethod.POST)
    public ModelAndView singlemailsend( EmailSendDto emailSendDto ) {
        //拆分邮件数据，组装mq消息格式，发送邮件处理mq，返回数据
        String emailMessJson = JsonUtil.toJsonString(emailSendDto);
        ModelAndView mv = new ModelAndView();
        producer.sendMessage(emailQueue, emailMessJson);
        mv.setViewName("list");
        return mv;
    }

    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public String mail() {
        System.out.println("MAIL");
        return "mail_info";
    }
}
























