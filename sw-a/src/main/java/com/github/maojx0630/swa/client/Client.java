package com.github.maojx0630.swa.client;

import com.github.maojx0630.swa.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-24 15:04
 * @description:
 */
@FeignClient(name = "swb")
public interface Client {

	@PostMapping("/")
	UserInfo get(@RequestBody UserInfo userInfo);
}
