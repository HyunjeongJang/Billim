package com.web.billim.controller.config;

import com.web.billim.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//
//@RequiredArgsConstructor
//@EnableWebSecurity
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final MemberService memberService;
//
//    @Override
//    public void configure(WebSecurity web){
//        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http
//                .authorizeRequests()
//                .antMatchers("/","/member/signup").permitAll()
//                .anyRequest().authenticated()

//                .and()
//                .formLogin()
//                .loginPage("/member/signup")
//                .defaultSuccessUrl("/")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true);
//
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }
//
//
//}
