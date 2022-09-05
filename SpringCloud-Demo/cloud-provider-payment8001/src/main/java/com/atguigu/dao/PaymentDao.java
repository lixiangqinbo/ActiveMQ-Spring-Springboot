package com.atguigu.dao;

import com.atguigu.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao {

    public int save(Payment payment);
    public Payment getPaymentById(@Param("id")Long id);
}
