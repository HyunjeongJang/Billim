package com.web.billim.security.config;

import com.web.billim.security.filter.CustomAuthenticationFilter;
import com.web.billim.security.handler.CustomLoginFailureHandler;
import com.web.billim.security.handler.CustomLoginSuccessHandler;
import com.web.billim.security.provider.CustomAuthenticationProvider;
import com.web.billim.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final CustomLoginSuccessHandler customLoginSuccessHandler;
    private final CustomLoginFailureHandler customLoginFailureHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

//        모든 작업 끝나면 경로마다 권한 부여해야 함
        http.authorizeRequests()
                .antMatchers("/member").authenticated()
//                .antMatchers("/admin").hasAuthority("ADMIN")   //인증 사용자만 허용
//                .antMatchers("/welcome").authenticated()   //인증 사용자만 허용
                .antMatchers("/login").anonymous();    //인증되지 않은 사용자만 허용
//                .antMatchers("/join").permitAll()    //모든 사용자 허용
//                .anyRequest().authenticated();

        http.formLogin()
                .usernameParameter("userId")
                .passwordParameter("password")
                .loginPage("/member/login")
                .permitAll()
                .and()
                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


        http.logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);   // 세션 날리기

//        http.exceptionHandling()
//                .accessDeniedPage("/error");  // 에러 페이지 만들게되면 설정해도 좋을 듯
    }


    //1. 가장 첫 요청이 Filter를 통해 들어옴 (FilterChain을 거치면서 줄줄이 수행함)
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/loginProcess");
        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler);
        customAuthenticationFilter.setAuthenticationFailureHandler(customLoginFailureHandler);
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    //2. 반환한 데이터를 인증처리 후 인증된 토큰을 AuthenticationManager에게 반환
    @Override   // AuthenticationManagerBuilder : 인증을 수행하기 위해 ProviderManager를 생성
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean //CustomAuthenticationProvider : 인증 처리 핵심 로직
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(userService, bCryptPasswordEncoder());
    }

    // AuthenticationManager를 bean으로 등록하기 위한 메소드
    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    // AuthenticationManager는 AuthenticationFilter에게 토큰 전달
    @Bean
    @Override // AuthenticationManager 클래스를 오버라이딩해서 Bean으로 등록
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean(); // 회원수정 후에 세션을 유지
    }

}