package com.web.billim.mail.controller;

import com.web.billim.mail.domain.MailTo;
import com.web.billim.mail.service.MailService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/send")
    public String sendTestMail(@RequestParam String to) throws Exception {
         String confirm = mailService.sendMail(to);
        return confirm;
    }

    @PostMapping("/mail/checkEmail")
    public Boolean checkEmail(@RequestParam String checkEmail){
        Boolean result = mailService.checkAuthCode(checkEmail);
        return result;
    }
}

