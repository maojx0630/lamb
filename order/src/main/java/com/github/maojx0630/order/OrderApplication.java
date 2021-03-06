package com.github.maojx0630.order;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-22 15:29
 * @description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableDistributedTransaction
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class);
	}
}
