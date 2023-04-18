package com.web.billim.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.billim.client.service.IamPortService;
import com.web.billim.coupon.domain.CouponIssue;
import com.web.billim.coupon.repository.CouponIssueRepository;
import com.web.billim.order.dto.response.PaymentInfoResponse;
import com.web.billim.payment.domain.Payment;
import com.web.billim.payment.dto.PaymentCommand;
import com.web.billim.payment.dto.PaymentInfoDto;
import com.web.billim.payment.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final IamPortService iamPortService;
    private final PaymentAmountCalculateService paymentAmountCalculateService;
    private final CouponIssueRepository couponIssueRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public PaymentInfoResponse payment(PaymentCommand command) {
        CouponIssue coupon = couponIssueRepository.findById(command.getCouponIssueId()).orElse(null);

        // 하나의 사용자는 하나의 결제만 진행할 수 있다.
        // TODO: 진짜 아직도 여전히 쓸수있는 쿠폰일까?
        // TODO: 진짜 아직도 그만큼의 포인트가 남아있을까?
        PaymentInfoDto info = paymentAmountCalculateService.calculateAmount(command.getOrder(), coupon, command.getUsedPoint());
        String merchantUid = iamPortService.generateMerchantUid();

        Payment payment = paymentRepository.save(Payment.of(merchantUid, info));
        return PaymentInfoResponse.from(payment);
    }

}
// FE 에서 사용자 입력 받고
// 서버 API 호출(/order) 해서 결제금액 받고(merchant_uid, amount..)
// IMP request_pay 호출
// http://localhost:8080/order/validation


