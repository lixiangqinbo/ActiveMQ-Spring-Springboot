package com.atguigu.feignservice;

import com.atguigu.entities.CommonResult;
import com.atguigu.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "consul-provider-payment")
public interface FeignOrderService {

    @GetMapping("/payment")
    public CommonResult save(Payment payment);

    @GetMapping("/getById/{id}")
    public CommonResult getById(@PathVariable("id") Long id);

    @GetMapping("/discover")
    public CommonResult discoverService();

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut();
}
