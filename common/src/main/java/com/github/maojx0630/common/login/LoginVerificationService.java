package com.github.maojx0630.common.login;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-21 16:13
 * @description:
 */
@SuppressWarnings("all")
public class LoginVerificationService {

	private ValueOperations<String,String> valueOperations;

	private RedisTemplate redisTemplate;

	LoginVerificationService(RedisTemplate redisTemplate){
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		valueOperations=redisTemplate.opsForValue();
		this.redisTemplate=redisTemplate;
	}


}
