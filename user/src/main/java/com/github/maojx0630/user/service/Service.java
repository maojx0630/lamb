package com.github.maojx0630.user.service;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.maojx0630.user.mapper.Mapper;
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

	@Transactional
	@LcnTransaction(propagation= DTXPropagation.SUPPORTS)
	public Integer test(String name){
		 mapper.update(name,"2");
		 return 1;
	}
}
