package com.atguigu.controller;

import com.atguigu.entities.CommonResult;
import com.atguigu.entities.Payment;
import com.atguigu.feignservice.FeignOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;


@RestController
public class OrderController {
    //private final String URL = "http://localhost:8001";
    // 此服务下多个服务提供，方便负载均衡
    private final String URL = "http://consul-provider-payment";

    @Autowired
    private FeignOrderService feignOrderService;

    //用户直接get调用 但是服务端实际上是post
    @GetMapping("/consumer/payment")
    public CommonResult save(Payment payment){

        CommonResult commonResult = feignOrderService.save(payment);

        return commonResult;
    }
    @GetMapping("/consumer/getById/{id}")
    public CommonResult getById(@PathVariable("id") Long id){

        CommonResult commonResult = feignOrderService.getById(id);

        return commonResult;
    }

    @GetMapping("/consumer/discover")
    public CommonResult discoverService(){

        CommonResult commonResult = feignOrderService.discoverService();

        return commonResult;
    }

    //测试配置超时的
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        String msg = feignOrderService.paymentFeignTimeOut();
        return msg;
    }

}
