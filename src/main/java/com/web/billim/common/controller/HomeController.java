package com.web.billim.common.controller;

import com.web.billim.security.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal User user) {
        if(user != null){
            model.addAttribute("user", user);
        }
        return "pages/home";
    }
}
