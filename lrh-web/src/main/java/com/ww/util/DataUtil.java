package com.ww.util;

import com.ww.exception.MyDataException;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/10/31.
 */
public class DataUtil {
    public static boolean isNotEmpty(Object[] args) {
        return !isEmpty(args);
    }

    public static boolean isEmpty(Object[] args) {
        if(args==null)return true;
        if(args.length==0)return true;
        return false;
    }

    public static String getMethodArgs(Object[] args) {
        if(isEmpty(args)) return "";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<args.length;i++){
            sb.append("参数"+i+":"+args[i].toString()+"\r\t");
        }
        return sb.toString();
    }

    public static boolean passwdIsRight(String passwordTrue, String passwordInput)
            throws NoSuchAlgorithmException, MyDataException {
        String passwordInputRe = encryptionMD5(passwordInput);
        /*System.out.println("passwordInput="+passwordInput);
        System.out.println("passwordInputRe="+passwordInputRe);
        System.out.println("passwordTrue="+passwordTrue);
        System.out.println("passwordInputRe="+passwordInputRe.replace('�',' '));
        String s="�";*/
        if(passwordTrue.equals(passwordInputRe)){
            return true;
        }
        return false;
    }

    public static String encryptionMD5(String str) throws MyDataException, NoSuchAlgorithmException {
        /**
         * 先按照业务规则对输入的参数进行验证
         * 不符合规则的输入 抛出相应的异常
         */
        if(str==null||str.length()==0){
            //如果输入的参数为空 则主动抛出一个异常 ，提示信息是加密的数据为空
            throw new MyDataException("要加密的数据为空",new Exception());
        }
        /**
         * 如果输入不为空 ，调用方法进行加密
         */
        try{
            //获取加密类的对象
            MessageDigest md5 = MessageDigest.getInstance("md5");
            //将字符串进行加密 加密的结果是byte 数组 所以需要用数组对象先接收
            //byte[] byteEncryption = md5.digest(str.getBytes());
            //将数组对象转换成String 并返回
            return new String(md5.digest(str.getBytes()));
        }catch (NoSuchAlgorithmException e) {
            throw e;
        }
    }

    /**
     * 生成一个随机字符串，待实现
     * @return
     */
    public static String geToken() {
        return RandomStringUtils.randomAlphanumeric(20);
    }

    /**
     * 获取总页数
     * @param totalCounts 记录总条数
     * @param itemsPerPage 每一页条数
     * @return
     */
    public static int getTotalPages(int totalCounts, int itemsPerPage) {
        if(itemsPerPage<1) return 0;
        if(totalCounts==0) return 0;
        if(totalCounts%itemsPerPage==0)return (totalCounts/itemsPerPage);
        return (totalCounts/itemsPerPage)+1;
    }

    //判断字符类型是null或者'0'返回true
    public static boolean intIsEmpty(String crruntPage) {
        if(crruntPage==null)return true;
        if("0".equals(crruntPage))return true;
        return false;
    }
}
