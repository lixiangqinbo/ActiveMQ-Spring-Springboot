package org.mq.demo.mqconfig;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class MQConfig {
    @Value("${queue.name}")
    private String queue;

    @Value("${topic.name}")
    private String topic;

    @Bean
    public Queue activeMQQueue(){
        return new ActiveMQQueue(queue);
    }

    @Bean
    public Topic activeMQTopic(){
        return new ActiveMQTopic(topic);
    }
}
