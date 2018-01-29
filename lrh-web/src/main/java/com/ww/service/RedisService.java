package com.ww.service;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/15.
 */
@Service
public class RedisService {
    @Resource(name="redisTemplate")
    private StringRedisTemplate redisTemplate;
    @Resource(name="jedisPool")
    private JedisPool jedisPool;
    /**
     * 设置键值对
     * @param key
     * @param value
     */
    public void setStringValue(String key,String value){
        redisTemplate.delete(key);
        redisTemplate.opsForValue().set(key, value);

    }
    /**
     * 获取key对应的value，适用于键值对数据
     * @param key
     * @return
     */
    public String getStringValueRedisTemplate(String key){
        return redisTemplate.opsForValue().get(key);
    }


    /**
     * 获取redis的hash数据，用 RedisTemplate 对象
     * @param key
     * @return
     */
    public Map<String,String> getHashValuesRedisTemplate(String key){
        BoundHashOperations<String, String, String> bo = redisTemplate.boundHashOps(key);
        if(bo==null||bo.size()==0){
            return null;
        }else{
        }
        Map map = redisTemplate.boundHashOps(key).entries();
        if(map==null||map.size()==0){
            return null;
        }
        return map;
    }

    /**
     * 获取redis的hash数据，用Jedis对象
     * @param key
     * @return
     */
    public Map<String,String> getHashValuesJedis(String key){
        Map<String,String> map = new HashMap<String, String>();
        Jedis jedis = jedisPool.getResource();
        Set<String> set =  jedis.hkeys(key);
        if(set==null||set.size()==0){
            return null;
        }
        Iterator<String> iter=set.iterator();
        while (iter.hasNext()){
            String key1 = iter.next();
            String value1=jedis.hmget(key,key1).get(0);
            System.out.println(key1+":"+value1);
            map.put(key1,value1);
        }
        return map;
    }
}












