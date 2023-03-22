package com.web.billim.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/memberEnroll")
    public String memberEnroll(){
        return "page/memberSignUp";
    }


}
