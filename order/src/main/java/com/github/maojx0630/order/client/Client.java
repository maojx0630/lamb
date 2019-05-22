package com.github.maojx0630.order.client;

import com.github.maojx0630.common.response.result.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-22 16:03
 * @description:
 */
@FeignClient("user")
public interface Client {

	@GetMapping("/")
	ResponseResult<Integer> test(@RequestParam("name") String name);

}
