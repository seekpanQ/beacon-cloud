package com.mashibing.webmaster.service;

import com.mashibing.webmaster.entity.SmsUser;

/**
 * 用户信息的service
 */
public interface SmsUserService {

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    SmsUser findByUsername(String username);
}
