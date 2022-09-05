package com.atguigu.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration
public class SpringBootConfig {

    @ConditionalOnMissingBean(RestTemplate.class)
    @Bean
    @LoadBalanced // 开启负载均衡的策略 默认是轮询的方式（当有多个服务集群后必须开启，否则消费方不知道调用那个提供方）
    public RestTemplate registerRestTemplate(){
        return new RestTemplate();
    }

}
