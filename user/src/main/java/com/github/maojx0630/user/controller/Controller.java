package com.github.maojx0630.user.controller;

import com.github.maojx0630.user.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-20 11:12
 * @description:
 */
@RestController
public class Controller {

	@Autowired
	private Service service;

	@GetMapping("/")
	public Integer test(@RequestParam("name") String name){
		return service.test(name);
	}

}
