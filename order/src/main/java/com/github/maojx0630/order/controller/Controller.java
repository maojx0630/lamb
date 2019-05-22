package com.github.maojx0630.order.controller;

import com.github.maojx0630.common.response.result.ResponseResult;
import com.github.maojx0630.order.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-22 15:35
 * @description:
 */
@RestController
public class Controller {

	@Autowired
	private Client client;

	@GetMapping("/")
	public ResponseResult<String> test(String name){
		return client.test(name);
	}
}
