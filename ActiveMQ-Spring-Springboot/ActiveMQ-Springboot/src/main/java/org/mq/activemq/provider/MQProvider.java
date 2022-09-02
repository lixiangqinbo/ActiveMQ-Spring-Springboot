package org.mq.activemq.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.UUID;
@Service
public class MQProvider {
    @Autowired
    private Queue queue;//队列

    @Autowired
    private Topic topic; //主题

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    //间隔5s发送一次消息
    @Scheduled(fixedDelay = 5000)
    public void productMessage() throws JMSException {
        // 生产者生产并发送消息，此方法是send方法的加强版，传入队列 或者主题
        jmsMessagingTemplate.convertAndSend(topic,
                "消费者发送消息：" + UUID.randomUUID().toString().substring(0,5));
        System.out.println("消费者发送消息结束！");
    }
}
