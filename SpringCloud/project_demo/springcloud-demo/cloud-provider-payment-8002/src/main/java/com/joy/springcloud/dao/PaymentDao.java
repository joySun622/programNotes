package com.joy.springcloud.dao;

import com.joy.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author joysun
 * @create 2021-09-30 22:38
 */
@Component  //代替@Repository声明bean
@Mapper  //mybatis提供的，等价@MapperScan("com.joy.springcloud.dao")
//@MapperScan("com.joy.springcloud.dao")
public interface PaymentDao {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
