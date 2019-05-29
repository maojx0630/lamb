package com.github.maojx0630.swb.controller;

import com.github.maojx0630.swb.entity.UserInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
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
	public UserInfo get(@RequestBody UserInfo userInfo) {
		userInfo.setName("swb");
		return userInfo;
	}

	@GetMapping("/test")
	@ApiOperation(value = "测试swagger")
	@ApiImplicitParam(name = "name",value = "测试用名字",required = true,dataType = "String")
	public String test(String name) {
		return "swagger : " + name;
	}
}
