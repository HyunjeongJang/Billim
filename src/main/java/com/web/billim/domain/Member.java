package com.web.billim.domain;

import com.web.billim.type.MemberGrade;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
@Builder
@Getter
public class Member extends JpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MYSQL increment_auto 로 PK 를 생성
    @Column(name = "member_id")
    private int memberId;

    private String id;

    private String password;

    private String name;

    private String nickname;

    private String address;

    private String email;

    @Enumerated(EnumType.STRING)
    private MemberGrade grade;



}
