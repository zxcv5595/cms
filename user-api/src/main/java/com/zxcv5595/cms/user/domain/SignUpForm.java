package com.zxcv5595.cms.user.domain;

import java.time.LocalDate;
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
public class SignUpForm {

    private String email;
    private String name;
    private String password;
    private LocalDate birth;
    private String phone;

}
