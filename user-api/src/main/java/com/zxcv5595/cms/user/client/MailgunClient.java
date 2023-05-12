package com.zxcv5595.cms.user.client;

import com.zxcv5595.cms.user.client.mailgun.SendMailForm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mailgun", url = "https://api.mailgun.net/v3/")
@Qualifier("mailgun")
public interface MailgunClient {

    @PostMapping("sandbox0ad3869eaf5d4d19926aaee9a954936a.mailgun.org/messages")
    ResponseEntity<?> sendEmail(@SpringQueryMap SendMailForm form);

}
