package com.ww.mqlistener;

import org.apache.log4j.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by Administrator on 2017/9/19.
 */
public class QueueMessageListener  implements MessageListener {
    private final Logger logger = Logger.getLogger(QueueMessageListener.class);
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            logger.info("QueueMessageListener监听到了文本消息：\t"
                    + tm.getText());
            //do something ...
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
