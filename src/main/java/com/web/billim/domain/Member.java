package com.web.billim.domain;

import com.web.billim.type.MemberGrade;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
@Builder
@Getter
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MYSQL increment_auto 로 PK 를 생성
    @Column(name = "member_id")
    private int memberId;

    private String userId;

    private String password;

    private String name;

    private String nickname;

    private String address;

    private String email;

    @Enumerated(EnumType.STRING)
    private MemberGrade grade;

    private String profileImageUrl;

    @PrePersist
    public void prePersist(){
        this.profileImageUrl = this.profileImageUrl == null ? "https://billim.s3.ap-northeast-2.amazonaws.com/profile/default.png": this.profileImageUrl;
    }

//    public void setProfileImageUrl(String profileImageUrl) {
//        this.profileImageUrl = profileImageUrl;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return getUserId();
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
