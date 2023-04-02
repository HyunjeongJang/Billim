package com.web.billim.controller;


import com.web.billim.dto.request.MemberSignupRequest;
import com.web.billim.service.MemberService;
import com.web.billim.validation.CheckIdValidator;
import com.web.billim.validation.CheckNickNameValidator;
import com.web.billim.validation.CheckPasswordValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

//    @Autowired
    private final MemberService memberService;
    private final CheckIdValidator checkIdValidator;
    private final CheckNickNameValidator checkNickNameValidator;
    private final CheckPasswordValidator checkPasswordValidator;

    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkIdValidator);
        binder.addValidators(checkNickNameValidator);
        binder.addValidators(checkPasswordValidator);
    }

    @GetMapping("/member/login")
    public String memberLogin() {
        return "pages/member/login";
    }

    @GetMapping("/member/signup")
    public String memberSignUp() {
        return "pages/member/signup";
    }

    @PostMapping("/member/signup")
    public String memberSingUpProc(@Valid MemberSignupRequest memberSignupRequest,
                                   BindingResult bindingResult,
                                   Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("memberDto", memberSignupRequest);

            Map<String, String> validatorResult = memberService.validateHandling(bindingResult);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "pages/member/signup";
        }
        memberService.singUp(memberSignupRequest);

        return "pages/home";
    }


//    @PostMapping("/member/login")
//    public String memberLoginProc(@Valid MemberLoginRequest memberLoginRequest){
//            memberService.login(memberLoginRequest);
//    }

    @GetMapping("/member/myPage")
    public String myPage() {
        return "pages/myPage/myPurchaseList";
    }
}
