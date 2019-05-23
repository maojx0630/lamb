package com.github.maojx0630.dlx.production;

import com.github.maojx0630.dlx.config.DlxConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-23 17:16
 * @description:
 */
@RestController
public class Controller {

	@Autowired
	private RabbitTemplate rabbitTemplate;


	@GetMapping("/")
	public String test(){
		rabbitTemplate.convertAndSend(DlxConfiguration.DEAD_LETTER_EXCHANGE,
				DlxConfiguration.DEAD_LETTER_TEST_ROUTING_KEY,
				System.currentTimeMillis(), message -> {
			message.getMessageProperties().setExpiration(2000+"");
			return message;
		});
		return "666";
	}
}
