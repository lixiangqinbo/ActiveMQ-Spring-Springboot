package com.atguigu.controller;

import com.atguigu.entities.CommonResult;
import com.atguigu.entities.Payment;
import com.atguigu.service.PaymentService;
import com.atguigu.util.CodeMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment")
    public CommonResult save(@RequestBody Payment payment){
        int res = paymentService.save(payment);
        if (res>0) {
            return  new CommonResult(CodeMsg.SUCCESS_CODE,"SUCCESSFUL 执行端口："+serverPort,res);
        }
        return new CommonResult(CodeMsg.FAIL_CODE,"REQUEST FAIL 执行端口："+serverPort,null);
    }

    @GetMapping("/getById/{id}")
    public CommonResult getById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment!=null) {
            return  new CommonResult(CodeMsg.SUCCESS_CODE,"SUCCESSFUL 执行端口："+serverPort,payment);
        }
        return new CommonResult(CodeMsg.FAIL_CODE,"REQUEST FAIL 执行端口："+serverPort,null);
    }

    @GetMapping("/discover")
    public CommonResult discoverService(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("discover services :"+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("CLOUD-PAYMENT-SERVICE:"+instance.getHost()+","+instance.getServiceId()+","
            +instance.getPort()+","+instance.getUri());
        }
        return  new CommonResult(CodeMsg.SUCCESS_CODE,"SUCCESS_CODE",instances);
    }
}
