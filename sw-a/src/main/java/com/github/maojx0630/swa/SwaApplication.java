package com.github.maojx0630.swa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-24 14:59
 * @description:
 */
@SpringBootApplication
@EnableFeignClients
public class SwaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaApplication.class);
	}
}
