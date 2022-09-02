package org.mq.demo.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import javax.jms.TextMessage;
import java.util.UUID;

@Service
public class MQProvider {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        MQProvider mQProvider = (MQProvider)context.getBean("MQProvider");
        for (int i = 0; i <3 ; i++) { //模拟三条消息
            mQProvider.jmsTemplate.send((session) -> {
                TextMessage textMessage = session.createTextMessage("ActiveMQ+Spring:"+ UUID.randomUUID().toString()
                        .substring(0,5));
                System.out.println("ActiveMQ+Spring:发送第消息完成！");
                return textMessage;
            });
        }
    }

}
