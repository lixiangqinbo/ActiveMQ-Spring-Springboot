package org.mq.demo.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MQTopicConsumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationtopic.xml");
        MQTopicConsumer bean = context.getBean(MQTopicConsumer.class);
        //配置MessageListener 非阻塞监听消息
        bean.jmsTemplate.receiveAndConvert();
    }
}
