package com.web.billim.controller;

import com.web.billim.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

//    @GetMapping("/login")
//    public String login_page() {
//        return "login";
//    }

//    @RequestMapping(value = "/join", method = RequestMethod.GET)
//    public String join_page() {
//        return "join";
//    }
//
//    @RequestMapping(value = "/join", method = RequestMethod.POST)
//    public String joinUser(UserDto joinUser, RedirectAttributes redirectAttributes) {
//        userService.saveUser(joinUser);
//        redirectAttributes.addAttribute("userid", joinUser.getUserid());
//        return "redirect:/login";
//    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public String user_page() {
//        return "user";
//    }
//
//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public String admin_page() {
//        return "admin";
//    }
}
