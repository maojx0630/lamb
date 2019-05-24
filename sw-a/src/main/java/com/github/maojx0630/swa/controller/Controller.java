package com.github.maojx0630.swa.controller;

import com.github.maojx0630.swa.client.Client;
import com.github.maojx0630.swa.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-24 15:00
 * @description:
 */
@RestController
public class Controller {

	@Autowired
	private Client client;

	@GetMapping("/")
	public UserInfo get(){
		UserInfo userInfo=new UserInfo();
		userInfo.setId("swa");
		return client.get(userInfo);
	}
}
