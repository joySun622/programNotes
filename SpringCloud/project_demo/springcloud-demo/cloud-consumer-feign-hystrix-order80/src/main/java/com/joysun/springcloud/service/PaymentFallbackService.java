package com.joysun.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author joysun
 * @create 2021-10-08 14:51
 */
@Component
public class PaymentFallbackService implements PaymentHytrixService{

    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK , (┬＿┬)";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut , (┬＿┬)";
    }
}
