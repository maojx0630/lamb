package com.github.maojx0630.swb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-24 14:58
 * @description:
 */
@SpringBootApplication
@EnableFeignClients
public class SwbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwbApplication.class);
	}
}
