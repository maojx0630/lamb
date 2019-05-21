package com.github.maojx0630.common.qiniu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-21 16:50
 * @description:
 */
@Data
@Configuration
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "qiniu")
public class QiniuConfig {

	private String accessKey;

	private String secretKey;
}
