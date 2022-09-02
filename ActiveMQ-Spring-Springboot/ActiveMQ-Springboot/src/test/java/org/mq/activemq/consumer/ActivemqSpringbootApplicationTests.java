package org.mq.activemq.consumer;

import org.junit.jupiter.api.Test;
import org.mq.activemq.provider.MQProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jms.JMSException;

@SpringBootTest()
class ActivemqSpringbootApplicationTests {

	@Autowired
	private MQProvider mqProvider;
	//消息生产QUEUE
	@Test
	public void testSendQueue() throws JMSException {
		for (int i = 0; i <3 ; i++) {
			mqProvider.productMessage();
		}

	}
	//消息生产 TOPIC
	@Test
	public void testSendTopic() throws JMSException {
			mqProvider.productMessage();
	}

}
