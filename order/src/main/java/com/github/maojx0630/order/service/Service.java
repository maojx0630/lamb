package com.github.maojx0630.order.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.maojx0630.order.client.Client;
import com.github.maojx0630.order.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	@LcnTransaction
	public String test(String name) {
		mapper.update(name,"1");
		client.test(name);
		mapper.update(name,"3");
		return "123";
	}
}
