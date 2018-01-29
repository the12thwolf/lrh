package com.ww.mvc.controller;
import com.ww.service.ProducerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class MQController {

    private final Logger logger = Logger.getLogger(MQController.class);
    //队列名gzframe.demo
    @Resource(name="cupsQueueDestination")
    private Destination cupsQueueDestination;

    //队列消息生产者
    @Resource(name="producerService")
    private ProducerService producer;

    @RequestMapping(value="/list",method=RequestMethod.GET)
    public ModelAndView welcome(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        return mv;
    }


    @RequestMapping(value="/producer",method=RequestMethod.GET)
    public ModelAndView producer(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format( now );
        ModelAndView mv = new ModelAndView();
        mv.addObject("time", time);
        mv.setViewName("jms_producer");
        return mv;
    }

    @RequestMapping(value="/onsend",method=RequestMethod.POST)
    public ModelAndView producer(@RequestParam("message") String message) {
        ModelAndView mv = new ModelAndView();
        producer.sendMessage(cupsQueueDestination, message);
        mv.setViewName("list");
        return mv;
    }
}