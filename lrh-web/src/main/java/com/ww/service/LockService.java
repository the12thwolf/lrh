package com.ww.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/15.
 */
@Service
public class LockService {
    @Resource(name="redisTemplate")
    private StringRedisTemplate redisTemplate;

    public String getLock(String lockKey,String lockValue){
        return null;
    }
}
