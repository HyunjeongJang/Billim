package com.web.billim.dto.request;

import com.web.billim.domain.Member;
import com.web.billim.type.MemberGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    private int memberId;

    private String userId;

    private String password;

    private String name;

    private String nickname;

    private String address;

    private String email;


    private MemberGrade grade;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

//    @Override
//    public String getPassword() {
//        return getPassword();
//    }

    @Override
    public String getUsername() {
        return getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
