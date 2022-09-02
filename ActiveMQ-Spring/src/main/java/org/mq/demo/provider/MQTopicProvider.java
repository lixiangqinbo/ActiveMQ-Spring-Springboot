package org.mq.demo.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;
import java.util.UUID;

@Component
public class MQTopicProvider {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationtopic.xml");
        MQTopicProvider bean = context.getBean(MQTopicProvider.class);
        for (int i = 0; i <3 ; i++) {
            bean.jmsTemplate.send(session -> {
                TextMessage textMessage = session.createTextMessage("ActiveMQ+Spring:"+ UUID.randomUUID().toString()
                        .substring(0,5));
                System.out.println("ActiveMQ+Spring:发送第消息完成！");
                return textMessage;
            });
        }

    }
}
