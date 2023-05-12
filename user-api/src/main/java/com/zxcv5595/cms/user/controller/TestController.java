package com.zxcv5595.cms.user.controller;

import com.zxcv5595.cms.user.service.test.EmailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final EmailSendService emailSendService;

    @GetMapping
    public ResponseEntity<?> sendTestEmail(){

        return ResponseEntity.ok(emailSendService.sendEmail());
    }


}
