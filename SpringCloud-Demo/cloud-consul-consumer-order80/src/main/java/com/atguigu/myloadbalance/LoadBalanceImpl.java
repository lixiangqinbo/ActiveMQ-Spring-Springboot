package com.atguigu.myloadbalance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 手写 负载均衡算法
 */
@Component
@Slf4j
public class LoadBalanceImpl implements DIYLoadBalance{

    private final AtomicInteger atomicInteger= new AtomicInteger(0);


    // 轮询算法 设计到JUC的CAS 有时间去补课
    public int getInstance(){
        int next;
        int current =atomicInteger.get();
        do{
            next = current >Integer.MAX_VALUE?0:current+1;

        }while (!this.atomicInteger.compareAndSet(current, next));
        log.info("服务被调用了+"+next+"次");
        return next;
    }
    @Override
    public ServiceInstance getInstance(List<ServiceInstance> serviceInstances) {
        int index = getInstance() % serviceInstances.size();
        ServiceInstance serviceInstance = serviceInstances.get(index);
        return serviceInstance;
    }
}
