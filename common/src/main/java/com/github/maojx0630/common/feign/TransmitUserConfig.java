package com.github.maojx0630.common.feign;

import feign.RequestTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author MaoJiaXing
 */
@Configuration
@ConditionalOnClass(RequestTemplate.class)
@ConditionalOnWebApplication
public class TransmitUserConfig implements WebMvcConfigurer {

	@Bean
	public TransmitUserInfoFeignClientIntercepter transmitUserInfoFeignClientIntercepter(){
		return new TransmitUserInfoFeignClientIntercepter();
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TransmitUserInfoInterceptor()).addPathPatterns("/**");
	}
}
