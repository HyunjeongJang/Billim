package com.web.billim.dto;

import com.web.billim.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    private Long code;
    private String userId;
    private String password;
//    private String authRole;

    @Builder
    public MemberDto(Long code, String userId, String password) {
        this.userId = userId;
        this.password = password;
//        this.authRole = authRole;
    }

    public Member toEntity(){
        return Member.builder()
                .userId(userId)
                .password(password)
//                .authRole(authRole)
                .build();
    }
}
