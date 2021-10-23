package com.joysun.service.impl;

import com.joysun.dao.UserInfoMapper;
import com.joysun.pojo.UserInfo;
import com.joysun.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author joysun
 * @create 2021-10-22 17:15
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decrMoney(String username, int money) {
        /***
         * 账户金额递减
         * @param username
         * @param money
         */
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(username);
            userInfo.setMoney(userInfo.getMoney()-money);
            int count = userInfoMapper.updateByPrimaryKeySelective(userInfo);
            System.out.println("添加用户受影响行数："+count);
            //  int q=10/0;
    }
}
