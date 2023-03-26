package com.web.billim.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MYSQL increment_auto 로 PK 를 생성
    @Column(name = "member_id")
    private int member_id;

    private String id;

    private String password;

    private String name;

    private String nickname;

    private String address;

    private String email;

    private String grade;


}
