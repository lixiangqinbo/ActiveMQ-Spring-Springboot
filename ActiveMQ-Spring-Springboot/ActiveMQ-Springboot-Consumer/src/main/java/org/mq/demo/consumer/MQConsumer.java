package org.mq.demo.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;


@Component
public class MQConsumer {

   // @JmsListener(destination = "${queue.name}") //监听队列
    @JmsListener(destination = "${topic.name}") //监听主题
    public void receiveMessage(TextMessage textMessage) throws JMSException {
        String text = textMessage.getText();
        System.out.println("***消费者收到的消息:" + text);
    }

}
