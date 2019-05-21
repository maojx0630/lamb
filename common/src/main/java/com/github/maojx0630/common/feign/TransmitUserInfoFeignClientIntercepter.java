package com.github.maojx0630.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * feign的request拦截器设置header进行用户信息传递
 * @author MaoJiaXing
 */
public class TransmitUserInfoFeignClientIntercepter implements RequestInterceptor {

	private static final Logger LOGGER= LoggerFactory.getLogger(TransmitUserInfoFeignClientIntercepter.class);

	@Override
	public void apply(RequestTemplate requestTemplate) {
		String id = UserUtils.getLoginUserId();
		if (StringUtils.isNoneBlank(id)) {
			LOGGER.debug("feign发起调用 传入userInfo {} 调用uri {}", id, requestTemplate.url());
			requestTemplate.header(UserUtils.KEY_USER_IN_HTTP_HEADER, id);
		}
	}
}
