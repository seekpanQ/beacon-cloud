package com.mashibing.webmaster.service.impl;

import com.mashibing.webmaster.entity.SmsUser;
import com.mashibing.webmaster.entity.SmsUserExample;
import com.mashibing.webmaster.mapper.SmsUserMapper;
import com.mashibing.webmaster.service.SmsUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SmsUserServiceImpl implements SmsUserService {

    @Resource
    private SmsUserMapper smsUserMapper;

    @Override
    public SmsUser findByUsername(String username) {
        //1、封装查询条件
        SmsUserExample example = new SmsUserExample();
        SmsUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        //2、基于userMapper查询
        List<SmsUser> list = smsUserMapper.selectByExample(example);
        //3、返回
        return list != null ? list.get(0) : null;
    }
}
