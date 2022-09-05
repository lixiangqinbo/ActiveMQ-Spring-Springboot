package com.atguigu.myloadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
public interface DIYLoadBalance {
    ServiceInstance getInstance(List<ServiceInstance> serviceInstances);
}
