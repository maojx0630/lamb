package com.github.maojx0630.common.feign;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设置和移除获取到的登陆信息
 * @author MaoJiaXing
 */
public class TransmitUserInfoInterceptor implements HandlerInterceptor {

	private static final Logger LOGGER= LoggerFactory.getLogger(TransmitUserInfoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String userId = request.getHeader(UserUtils.KEY_USER_IN_HTTP_HEADER);
		if (StringUtils.isNotBlank(userId)) {
			UserUtils.setUserId(userId);
			LOGGER.debug("从header中获取到userId，userId {} ，请求uri {}", userId, request.getRequestURI());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) {
		LOGGER.debug("移除登陆信息防止内存泄漏");
		UserUtils.remove();
	}
}
