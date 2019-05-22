package com.github.maojx0630.order.controller;

import com.github.maojx0630.order.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-22 15:35
 * @description:
 */
@RestController
@Slf4j
public class Controller {

	@Autowired
	private Service service;

	@GetMapping("/")
	public String test(String name){
		return service.test(name);
	}
}
