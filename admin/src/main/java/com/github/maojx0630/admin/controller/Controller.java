package com.github.maojx0630.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-20 11:12
 * @description:
 */
@RestController
@RefreshScope
public class Controller {

	@Value("${test}")
	private String string;

	@GetMapping("/")
	public Object test(){
		return string;
	}

}
