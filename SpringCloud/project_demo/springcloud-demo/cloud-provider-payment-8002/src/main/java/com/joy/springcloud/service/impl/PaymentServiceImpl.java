package com.joy.springcloud.service.impl;

import com.joy.springcloud.dao.PaymentDao;
import com.joy.springcloud.entities.Payment;
import com.joy.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author joysun
 * @create 2021-09-30 22:59
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired //Spring提供，默认Bytype,再byName
    //@Resource // jdk提供，默认byName,后byType
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
