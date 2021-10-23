package com.joy.springcloud.service;

/**
 * @author joysun
 * @create 2021-10-08 10:55
 */
public interface PaymentService {
    public String paymentInfo_OK(Integer id);
    public String paymentInfo_TimeOut(Integer id);

    public String paymentCircuitBreaker(Integer id);
}
