package com.component.test.math;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

/**
 * Created by Administrator on 2017/11/22.
 */
public class RandomTest {
    public static void main(String[] args){
        for(int i=0;i<10;i++){
            //System.out.println(RandomUtils.nextLong());
            //System.out.println(RandomStringUtils.randomAlphabetic(22));
            System.out.println(RandomStringUtils.randomAlphanumeric(22));
            //System.out.println(RandomStringUtils.randomNumeric(22));
        }

    }
}
