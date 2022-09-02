package org.mq.demo.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


@Service
public class MQConsumer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        MQConsumer mQConsumer = (MQConsumer) context.getBean(MQConsumer.class);
        while(true){
            String receive = (String) mQConsumer.jmsTemplate.receiveAndConvert();
            if (receive==null){
                break;
            }
            System.out.println("ActiveMQ+Spring:消费完成！"+receive);
        }




    }
}
