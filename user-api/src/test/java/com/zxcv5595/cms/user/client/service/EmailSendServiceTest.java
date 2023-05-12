package com.zxcv5595.cms.user.client.service;

import com.zxcv5595.cms.user.service.test.EmailSendService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailSendServiceTest {

    @Autowired
    private EmailSendService emailSendService;

    @Test
    public void EmailTest(){
        //given
        //when
        //then
        String response = String.valueOf(emailSendService.sendEmail());
        System.out.println(response);
    }
}