package org.mq.activemq.consumer;

import org.junit.runner.RunWith;
import org.mq.activemq.ActivemqSpringbootApplication;
import org.mq.activemq.provider.MQProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;

@SpringBootTest(classes = ActivemqSpringbootApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {
    @Autowired
    private MQProvider mqProvider;
    //消息生产QUEUE
    @org.junit.Test
    public void testSendQueue() throws JMSException {
        for (int i = 0; i <3 ; i++) {
            mqProvider.productMessage();
        }

    }
    //消息生产 TOPIC
    @org.junit.Test
    public void testSendTopic() throws JMSException {
        mqProvider.productMessage();
    }
}
