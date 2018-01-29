package com.ww.util;

import com.ww.dto.EmailSendDto;
import net.sf.json.JSONObject;

/**
 * Created by Administrator on 2017/9/24.
 */
public class JsonUtil {
    /**
     * 将简单的javabean 转成String对象
     * @param obj
     * @return
     */
    public static String toJsonString(Object obj) {
        if(null==obj)return null;
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject.toString();
    }

    /**
     * 将json字符串转换成javaBean
     * @param objStr
     * @param objClass
     * @return
     */
    public static Object toJavaBean(String objStr, Class<EmailSendDto> objClass) {
        JSONObject myJsonObject = JSONObject.fromObject(objStr);
        Object obj = JSONObject.toBean(myJsonObject,objClass);
        return obj;
    }

    public static boolean dataIsOK(EmailSendDto emailSendDto) {
        return true;
    }
}
