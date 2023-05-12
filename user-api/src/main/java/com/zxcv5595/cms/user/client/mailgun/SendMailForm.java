package com.zxcv5595.cms.user.client.mailgun;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendMailForm {
        private String from;
        private String to;
        private String subject;
        private String text;
}
