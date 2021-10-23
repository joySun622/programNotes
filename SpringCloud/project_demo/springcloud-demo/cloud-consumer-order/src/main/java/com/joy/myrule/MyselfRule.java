package com.joy.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Configuration;

/**
 * @author joysun
 * @create 2021-10-05 13:10
 */
@Configuration
public class MyselfRule {

    public IRule myRule(){
        return new RandomRule();//定义规则为随机
    }
}
