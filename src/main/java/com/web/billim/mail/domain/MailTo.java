package com.web.billim.mail.domain;

import lombok.Data;

@Data
public class MailTo {
    private String address;
    private String title;
    private String message;
}
