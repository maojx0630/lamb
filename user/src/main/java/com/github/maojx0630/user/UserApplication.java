package com.github.maojx0630.user;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-20 11:09
 * @description:
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableDistributedTransaction
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class);
	}
}
