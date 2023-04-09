package com.web.billim.dto.response;

import com.web.billim.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindIdResponse {

    private String userId;

    public static FindIdResponse from(Member member) {
        return new FindIdResponse(member.getUserId());

    }
}
