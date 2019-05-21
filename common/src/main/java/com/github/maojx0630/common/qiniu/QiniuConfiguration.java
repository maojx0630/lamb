package com.github.maojx0630.common.qiniu;

import com.qiniu.common.Zone;
import com.qiniu.util.Auth;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-21 16:38
 * @description:
 */
@Configuration
@ConditionalOnClass(Auth.class)
public class QiniuConfiguration {

	@Bean
	@ConditionalOnMissingBean(QiniuService.class)
	public QiniuService getQiniuService(QiniuConfig qiniuConfig){
		QiniuManager qiniuManager=new QiniuManager(qiniuConfig, Zone.zone0());
		return new QiniuService(qiniuManager);
	}
}
