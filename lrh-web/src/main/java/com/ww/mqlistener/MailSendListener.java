package com.ww.mqlistener;
import com.ww.component.MailCompment;
import com.ww.dto.EmailSendDto;
import com.ww.dto.MailBackSendResult;
import com.ww.util.JsonUtil;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by Administrator on 2017/9/20.
 */
public class MailSendListener implements MessageListener  {
    @Resource
    private MailCompment mailCompment;

    private final Logger logger = Logger.getLogger(MailSendListener.class);

    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        if(tm==null){
            logger.error("接收到的信息为空，不予发送");
        }
        try {
            String mailMes = tm.getText();
            EmailSendDto emailSendDto = (EmailSendDto) JsonUtil.toJavaBean(mailMes,EmailSendDto.class);
            MailBackSendResult bakInfo = mailCompment.sendMail(emailSendDto);
            if("0000".equals(bakInfo.getErrorCode())){
                logger.info("发送邮件成功");
            }else{
                logger.error("发送邮件失败");
            }
        } catch (JMSException e) {
           logger.error("异常,错误码："+e.getErrorCode()+",错误描述："+e.getMessage());

        }
    }
}
