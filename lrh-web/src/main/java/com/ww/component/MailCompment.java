package com.ww.component;

import com.ww.dto.EmailSendDto;
import com.ww.dto.MailBackSendResult;
import com.ww.service.RedisService;
import com.ww.util.EmailUtil;
import com.ww.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/9/24.
 */
@Service
public class MailCompment {
    private final Logger logger = Logger.getLogger(MailCompment.class);
    @Resource
    private RedisService redisService;
    public MailBackSendResult sendMail(EmailSendDto emailSendDto)  {
        try{
            MailBackSendResult backInfo = new MailBackSendResult();
            //校验输入参数合法性
            if(!JsonUtil.dataIsOK(emailSendDto)) {
                backInfo.setErrorCode("0001");
                backInfo.setErrorCode("请求数据有误，发送终止");
                logger.error("请求数据有误，发送终止");
                return backInfo;
            }
            String charSet = "UTF-8";
            Map<String,String> mailSecMap = redisService.getHashValuesRedisTemplate(emailSendDto.getFajianren());
            //发件人邮箱数据，保存在redis中
            String myEmailSMTPHost = mailSecMap.get("host");
            // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
            // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com

            // 1. 创建参数配置, 用于连接邮件服务器的参数配置
            Properties props = new Properties();                    // 参数配置
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
            // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
            //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
            //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
        /*
        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        */
        /*http://m.mail.163.com/xm/static/html/126_android_2.html)
         SMTP发件服务器地址：smtp.126.com 安全类型：SSL 端口号：465 / 994
　　      注：1、163邮箱的SMTP服务器地址：smtp.163.com ，yeah邮箱的SMTP服务器地址：smtp.yeah.net；
　　　　   2、若安全类型选择“无”，则需将端口号修改为 25。*/

            // 2. 根据配置创建会话对象, 用于和邮件服务器交互
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log

            // 3. 创建一封邮件
            MimeMessage message = EmailUtil.createMimeMessage(session,emailSendDto,mailSecMap,charSet);
            //4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();
            // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
            //
            //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
            //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
            //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
            //
            //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
            //           (1) 邮箱没有开启 SMTP 服务;
            //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
            //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
            //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
            //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
            //
            //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
            transport.connect(emailSendDto.getFajianren(), mailSecMap.get("passwd"));

            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());

            // 7. 关闭连接
            transport.close();
            return EmailUtil.createSendSuccessInfo();
        }catch (Exception e){
            //e.printStackTrace();
            logger.error("发送邮件异常，原因:"+e.getMessage()+",原因:"+e.getLocalizedMessage());
            return  new MailBackSendResult("0002",e.getMessage());
        }

    }
}
