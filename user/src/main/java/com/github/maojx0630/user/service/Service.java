package com.github.maojx0630.user.service;

import com.github.maojx0630.user.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-22 16:26
 * @description:
 */
@org.springframework.stereotype.Service
public class Service {

	@Autowired
	private Mapper mapper;

	public Integer test(String name){
		return mapper.update(name,"2");
	}
}
