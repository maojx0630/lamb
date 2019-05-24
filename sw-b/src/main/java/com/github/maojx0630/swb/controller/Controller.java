package com.github.maojx0630.swb.controller;

import com.github.maojx0630.swb.entity.UserInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-24 15:02
 * @description:
 */
@RestController
public class Controller {

	@PostMapping("/")
	public UserInfo get(@RequestBody UserInfo userInfo){
		userInfo.setName("swb");
		return userInfo;
	}
}
