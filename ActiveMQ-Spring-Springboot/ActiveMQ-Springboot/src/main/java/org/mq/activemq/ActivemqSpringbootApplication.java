package org.mq.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJms //开启JMS服务
@SpringBootApplication
@EnableScheduling //开启定时调度服务
public class ActivemqSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqSpringbootApplication.class, args);
	}

}
