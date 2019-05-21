package com.github.maojx0630.common.redis;


import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@ConditionalOnBean(RedisTemplate.class)
public class RedisConfiguration {

    @Bean
    @ConditionalOnMissingBean(RedisObject.class)
    public RedisObject redisObject(RedisTemplate redisTemplate){
        return new RedisObject(redisTemplate);
    }
}
