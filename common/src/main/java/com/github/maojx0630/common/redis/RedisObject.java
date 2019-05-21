package com.github.maojx0630.common.redis;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
public class RedisObject<T> {

    private ValueOperations<String,T> valueOperations;

    private HashOperations<String,String,T> hashOperations;

    private RedisTemplate redisTemplate;

    RedisObject(RedisTemplate redisTemplate){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
        redisTemplate.setHashValueSerializer(new FastJsonRedisSerializer<>(Object.class));
        valueOperations=redisTemplate.opsForValue();
        hashOperations=redisTemplate.opsForHash();
        this.redisTemplate=redisTemplate;
    }

    public T get(String key){
        return valueOperations.get(key);
    }

    public Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    public void set(String key, T t){
        valueOperations.set(key,t);
    }

    public void set(String key, T t, long l){
        valueOperations.set(key,t,l, TimeUnit.SECONDS);
    }

    public void set(String key, T t, long l, TimeUnit timeUnit){
        valueOperations.set(key,t,l,timeUnit);
    }

    public boolean del(String key){
        return redisTemplate.delete(key);
    }

    public void hashPut(String key,String hashKey,T t){
        hashOperations.put(key, hashKey, t);
    }

    public T hashGet(String key,String hashKey){
        return hashOperations.get(key,hashKey);
    }

    public long hashDel(String key,String ...hashKey){
        return hashOperations.delete(key,hashKey);
    }

    public Boolean hashOperationsHasKey(String key,String hashKey){
        return hashOperations.hasKey(key,hashKey);
    }

    public boolean expire(String key,long l){
        return redisTemplate.expire(key,l,TimeUnit.SECONDS);
    }

    public boolean expire(String key,long l, TimeUnit timeUnit){
        return redisTemplate.expire(key,l,timeUnit);
    }

    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    public long getExpire(String key,TimeUnit timeUnit){
        return redisTemplate.getExpire(key,timeUnit);
    }

}
