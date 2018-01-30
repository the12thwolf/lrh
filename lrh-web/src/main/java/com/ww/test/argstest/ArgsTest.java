package com.ww.test.argstest;

/**
 * Created by Administrator on 2018/1/30.
 */
public class ArgsTest {
    public static void main(String[] args){
        System.out.println(argsSum(1,2,3,4,5,6,7,8,9,10));
    }
    public static int  argsSum(int... values){
        int sum=0;
        for(int value:values) sum +=value;
        return sum;
    }
}
