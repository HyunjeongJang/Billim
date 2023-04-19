package com.web.billim.mail.service;


import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@AllArgsConstructor
public class MailService {

    private JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    private static final String key=null;

    private MimeMessage createMessage(String to)throws Exception{
        createKey();
        MimeMessage  message = javaMailSender.createMimeMessage();
        message.addRecipients(RecipientType.TO, to);//보내는 대상
        message.setSubject("BILLIM 이메일 인증번호");//제목
        message.setText(setContext(key), "utf-8", "html");//내용
        message.setFrom(new InternetAddress("duatjgkr123@gmail.com","BILLIM"));//보내는 사람

        return message;
    }
    private String setContext(String code) { // 타임리프 설정하는 코드
        Context context = new Context();
        context.setVariable("code", code); // Template에 전달할 데이터 설정
        return templateEngine.process("mail", context); // mail.html
    }

    public static String createKey(){
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            int index = rnd.nextInt(3);
            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    break;
            }
        }
        return key.toString();
    }


    @Async
    public String sendMail(String to) throws Exception {
        MimeMessage message = createMessage(to);
        try{
            javaMailSender.send(message);
        }catch (MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return key;
    }

    public Boolean checkAuthCode(String checkEmail) {
        if(checkEmail.equals(key)){
            return true;
        }else{
            return false;
        }
    }
}
