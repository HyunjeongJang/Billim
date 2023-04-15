package com.web.billim.point.dto;

import java.time.LocalDateTime;

import com.web.billim.member.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppendPointCommand {

    private Member member;
    private int amount;
    private LocalDateTime expiredAt;

}
