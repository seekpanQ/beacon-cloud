package com.mashibing.webmaster.service;

import com.mashibing.webmaster.entity.SmsUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SmsUserServiceTest {

    @Autowired
    private SmsUserService smsUserService;


    @Test
    public void findByUserName() {
        SmsUser smsUser = smsUserService.findByUsername("admin");
        System.out.println(smsUser);
    }

}