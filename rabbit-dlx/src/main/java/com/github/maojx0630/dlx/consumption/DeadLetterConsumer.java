package com.github.maojx0630.dlx.consumption;

import com.github.maojx0630.dlx.config.DlxConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-23 17:14
 * @description:
 */
@Component
@RabbitListener(queues = DlxConfiguration.REDIRECT_QUEUE)
public class DeadLetterConsumer {

	@RabbitHandler
	public void test(Long number){
		long l=(System.currentTimeMillis()-number)/1000;
		System.out.println(l);
		System.out.println(l);
		System.out.println(l);
		System.out.println(l);
		System.out.println(l);
		System.out.println(l);
		System.out.println(l);
		System.out.println(l);
		System.out.println(l);
	}
}
