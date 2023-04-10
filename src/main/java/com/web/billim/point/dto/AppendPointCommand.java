package com.web.billim.point.dto;

import java.time.LocalDateTime;

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
