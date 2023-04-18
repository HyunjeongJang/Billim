package com.web.billim.mail.controller;

import com.web.billim.mail.domain.MailTo;
import com.web.billim.mail.service.MailService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/send")
    public MailTo sendTestMail(String email){
        MailTo mailTo = new MailTo();
        mailTo.setAddress(email);
        mailTo.setTitle("밤둘레 님이 발송한 이메일입니다.");
        mailTo.setMessage("안녕하세요. 반가워요!");

        mailService.sendMail(mailTo);
        return mailTo;
    }
}

