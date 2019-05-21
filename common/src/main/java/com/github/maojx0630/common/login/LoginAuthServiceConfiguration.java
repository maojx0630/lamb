package com.github.maojx0630.common.login;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-21 16:35
 * @description:
 */
@Configuration
@ConditionalOnBean(RedisTemplate.class)
public class LoginAuthServiceConfiguration {

	@Bean
	@ConditionalOnMissingBean(LoginAuthService.class)
	public LoginAuthService getLoginAuthService(RedisTemplate redisTemplate){
		return new LoginAuthService(redisTemplate);
	}
}
