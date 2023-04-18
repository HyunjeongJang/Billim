package com.web.billim.mail.service;

import com.web.billim.mail.domain.MailTo;
import lombok.NoArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class MailService {

    private JavaMailSender javaMailSender;

    public void sendMail(MailTo mail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());
        javaMailSender.send(message);
    }

}
