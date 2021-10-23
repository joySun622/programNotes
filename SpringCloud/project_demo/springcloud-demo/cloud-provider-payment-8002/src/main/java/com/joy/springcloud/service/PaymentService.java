package com.joy.springcloud.service;

import com.joy.springcloud.entities.Payment;

/**
 * @author joysun
 * @create 2021-09-30 22:54
 */
public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(Long id);
}
