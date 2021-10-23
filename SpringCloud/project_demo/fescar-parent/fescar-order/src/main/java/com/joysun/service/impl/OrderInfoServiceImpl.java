package com.joysun.service.impl;

import com.joysun.dao.OrderInfoMapper;
import com.joysun.feign.ItemInfoFeign;
import com.joysun.pojo.OrderInfo;
import com.joysun.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private ItemInfoFeign itemInfoFeign;
    /***
     * 添加订单
     * @param username
     * @param id
     * @param count
     */
    @Transactional
    @Override
    public void add(String username, int id, int count) {
        //添加订单
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setMessage("生成订单");
        orderInfo.setMoney(10);
        int icount = orderInfoMapper.insertSelective(orderInfo);
        System.out.println("添加订单受影响函数："+icount);
        //递减库存
        itemInfoFeign.decrCount(id,count);
    }
}
