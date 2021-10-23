package com.joy.springcloud.controller;

import com.joy.springcloud.entities.CommonResult;
import com.joy.springcloud.entities.Payment;
import com.joy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用，
 * 返回json数据不需要在方法前面加@ResponseBody注解了，
 * 但使用@RestController这个注解，
 * 就不能返回jsp,html页面，视图解析器无法解析jsp,html页面
 * 使用@Controller 注解，在对应的方法上，视图解析器可以解析return 的jsp,html页面，并且跳转到相应页面
 * 若返回json等内容到页面，则需要加@ResponseBody注解
 */
@RestController
@Slf4j
@EnableAutoConfiguration
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String port;

    @PostMapping(value="/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        System.out.println("======port=="+port);
        int result =paymentService.create(payment);
        log.info("*****插入结果："+result);
        if (result>0){
            return new CommonResult(200,"插入成功",result);
        }else {
            return new CommonResult<>(444,"插入失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public  CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
        System.out.println("======port=="+port);
        Payment payment=paymentService.getPaymentById(id);
        log.info("======查询结果"+payment);
        if(payment!=null){
            return new CommonResult<>(200,"查询成功",payment);
        } else{
            return new CommonResult<>(444,"查询失败,id="+id,null);
        }
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);//单位秒
        }catch (Exception e) {
            e.printStackTrace();
        }
        return port;
    }
}
