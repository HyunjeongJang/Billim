package com.web.billim.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/member/signUp")
    public String memberEnroll(){
        return "pages/memberSignUp";
    }


}
