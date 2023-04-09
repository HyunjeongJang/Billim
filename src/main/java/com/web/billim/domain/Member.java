package com.web.billim.domain;

import com.web.billim.type.MemberGrade;
import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
@Builder
@Getter
public class Member extends JpaEntity{

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

//    @PrePersist
//    public void prePersist(){
//        this.profileImageUrl = this.profileImageUrl == null ? "https://cdn-icons-png.flaticon.com/512/5580/5580988.png": this.profileImageUrl;
//    }

    @PrePersist
    public void prePersist(){
        this.profileImageUrl = this.profileImageUrl == null ? "https://cdn-icons-png.flaticon.com/512/8246/8246830.png": this.profileImageUrl;
    }


//    public void setProfileImageUrl(String profileImageUrl) {
//        this.profileImageUrl = profileImageUrl;
//    }

}
