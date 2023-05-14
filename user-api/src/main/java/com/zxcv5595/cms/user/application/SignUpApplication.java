package com.zxcv5595.cms.user.application;

import com.zxcv5595.cms.user.client.MailgunClient;
import com.zxcv5595.cms.user.client.mailgun.SendMailForm;
import com.zxcv5595.cms.user.domain.SignUpForm;
import com.zxcv5595.cms.user.domain.model.Customer;
import com.zxcv5595.cms.user.domain.model.Seller;
import com.zxcv5595.cms.user.exception.CustomException;
import com.zxcv5595.cms.user.exception.ErrorCode;
import com.zxcv5595.cms.user.service.customer.SignUpCustomerService;
import com.zxcv5595.cms.user.service.seller.SellerService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpApplication {

    private final MailgunClient mailgunClient;
    private final SignUpCustomerService signUpCustomerService;
    private final SellerService sellerService;

    public void customerVerify(String email, String code) {
        signUpCustomerService.verifyEmail(email, code);
    }

    public String sellerSignUp(SignUpForm form) {
        if (sellerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        } else {
            Seller seller = sellerService.signUp(form);
            String code = getRandomCode();
            SendMailForm mailForm = SendMailForm.builder()
                    .from("tester@test.com")
                    .to(form.getEmail())
                    .subject("Verification Email !!")
                    .text(getVerificationEmailBody(seller.getEmail(), seller.getName(),
                            "seller",
                            code))
                    .build();
            mailgunClient.sendEmail(mailForm);
            LocalDateTime verifiedAt = sellerService.ChangerSellerValidateEmail(
                    seller.getId(), code);
            return verifiedAt + "  회원 가입에 성공하였습니다.";
        }
    }

    public String customerSignUp(SignUpForm form) {
        if (signUpCustomerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        } else {
            Customer customer = signUpCustomerService.signUp(form);
            String code = getRandomCode();
            SendMailForm mailForm = SendMailForm.builder()
                    .from("tester@test.com")
                    .to(form.getEmail())
                    .subject("Verification Email !!")
                    .text(getVerificationEmailBody(customer.getEmail(), customer.getName(),
                            "customer",
                            code))
                    .build();
            mailgunClient.sendEmail(mailForm);
            LocalDateTime verifiedAt = signUpCustomerService.ChangerCustomerValidateEmail(
                    customer.getId(), code);
            return verifiedAt + "  회원 가입에 성공하였습니다.";
        }
    }

    public void sellerVerify(String email, String code) {
        sellerService.verifyEmail(email, code);
    }


    private String getRandomCode() {
        return RandomStringUtils.random(10, true, true);
    }

    private String getVerificationEmailBody(String email, String name, String type, String code) {
        StringBuilder sb = new StringBuilder();
        return sb.append("Hello ").append(name)
                .append("! Please Click the Link for verification.\n\n")
                .append("http://localhost:8081/signup/"+type+"/verify/?email=")
                .append(email)
                .append("&code=")
                .append(code).toString();
    }


}
