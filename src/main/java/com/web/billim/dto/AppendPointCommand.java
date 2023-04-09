package com.web.billim.dto;

import java.time.LocalDateTime;

import com.web.billim.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppendPointCommand {

    private int memberId;
    private int amount;
    private LocalDateTime expiredAt;

}
