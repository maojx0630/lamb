package com.github.maojx0630.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-20 11:12
 * @description:
 */
@RestController
public class Controller {

	@GetMapping("/")
	public String test(String name){
		return name;
	}

}
