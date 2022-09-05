package com.atguigu.controller;

import com.atguigu.entities.CommonResult;
import com.atguigu.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class OrderController {
    //private final String URL = "http://localhost:8001";
    // 此服务下多个服务提供，方便负载均衡
    private final String URL = "http://CLOUD-PAYMENT-SERVICE";
    @Autowired
    private RestTemplate restTemplate;
    //用户直接get调用 但是服务端实际上是post
    @GetMapping("/consumer/payment")
    public CommonResult save(Payment payment){
        CommonResult commonResult = restTemplate.postForObject(URL + "/payment", payment, CommonResult.class);
        return commonResult;
    }
    @GetMapping("/consumer/getById/{id}")
    public CommonResult getById(@PathVariable("id") Long id){
        CommonResult forObject = restTemplate.getForObject(URL + "/getById/" + id, CommonResult.class, id);
        return forObject;
    }

    @GetMapping("/consumer/discover")
    public CommonResult discoverService(){

        return restTemplate.getForObject(URL+"/discover", CommonResult.class);
    }


}
