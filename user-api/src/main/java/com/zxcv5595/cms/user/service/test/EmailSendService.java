package com.zxcv5595.cms.user.service.test;

import com.zxcv5595.cms.user.client.MailgunClient;
import com.zxcv5595.cms.user.client.mailgun.SendMailForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSendService {

    private final MailgunClient mailgunClient;

    public ResponseEntity<?> sendEmail() {
        SendMailForm form = SendMailForm.builder()
                .from("test@my.com")
                .to("zxcv5595@korea.ac.kr")
                .subject("test")
                .text("my Test")
                .build();

        return ResponseEntity.ok(mailgunClient.sendEmail(form));
    }
}
