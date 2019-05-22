package com.github.maojx0630.order.service;

import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.github.maojx0630.common.response.result.ResponseResult;
import com.github.maojx0630.order.client.Client;
import com.github.maojx0630.order.mapper.Mapper;
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

	@Autowired
	private Client client;

	@GlobalTransactional
	public String test(String name) {
		int i=mapper.update(name,"1");
		ResponseResult<Integer> responseResult=client.test(name);
		int k=mapper.update(name,"3");
		return "ok";
	}
}
