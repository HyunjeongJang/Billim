package com.web.billim.point.dto;

import com.web.billim.member.domain.Member;
import com.web.billim.payment.domain.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPointCommand {

    private Member member;
    private int amount;
    private LocalDateTime expiredAt;

    public static AddPointCommand from(Payment payment) {
        Member buyer = payment.getMember();
        int price = payment.getProductOrder().getPrice();
        int amount = price * (int) (buyer.getGrade().getSavedPointRate() / 100.0);
        return new AddPointCommand(buyer, amount, LocalDateTime.now().plusYears(1));
    }

}
