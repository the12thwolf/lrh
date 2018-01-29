package com.ww.util;

import com.ww.dto.EmailSendDto;
import com.ww.dto.MailBackSendResult;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/21.
 */
public class EmailUtil {

    public static MimeMessage createMimeMessage(Session session, EmailSendDto emailSendDto,
                                                Map<String, String> mailSecMap, String charSet)
            throws UnsupportedEncodingException, MessagingException {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(emailSendDto.getFajianren(), mailSecMap.get("nicheng"), charSet));
        // 3. To: 收件人
        String[] recvers = emailSendDto.getShoujianren().split(",");

        message.setRecipient(MimeMessage.RecipientType.TO,
                new InternetAddress(recvers[0], recvers[0].split("@")[0], charSet));
        //收件人地址，昵称，邮件编码
        //    To: 增加收件人（可选）
        for(int i=1;i<recvers.length;i++){
            message.addRecipient(MimeMessage.RecipientType.TO,
                    new InternetAddress(recvers[i], recvers[i].split("@")[0], charSet));
        }
        //    Cc: 抄送（可选）
        if(!StringUtil.stringIsBlank(emailSendDto.getChaosong())){
            String[] chaosong = emailSendDto.getChaosong().split(",");
            for(int i=0;i<chaosong.length;i++){
                message.setRecipient(MimeMessage.RecipientType.CC,
                        new InternetAddress(chaosong[i], chaosong[i].split("@")[0], charSet));
            }
        }
        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject(emailSendDto.getMailtitle(), charSet);
        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        message.setContent(emailSendDto.getMailtext(),"text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }

    public static MailBackSendResult createSendSuccessInfo() {
        MailBackSendResult backInfo =new MailBackSendResult("0000","邮件发送成功");
        return backInfo;
    }
}
