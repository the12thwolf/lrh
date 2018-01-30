package com.ww.test.argstest;

/**
 * Created by Administrator on 2018/1/30.
 */
public class ArgsTest {
    public int  argsSum(int... values){
        int sum=0;
        for(int value:values) sum +=value;
        return sum;
    }
}
