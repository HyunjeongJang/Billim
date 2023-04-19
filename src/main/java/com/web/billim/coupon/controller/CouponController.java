package com.web.billim.coupon.controller;

import com.web.billim.coupon.domain.CouponIssue;
import com.web.billim.coupon.dto.AvailableCouponResponse;
import com.web.billim.coupon.service.CouponService;
import com.web.billim.member.domain.Member;
import com.web.billim.product.dto.response.ProductDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    /*
     *  1. 사용 가능한 쿠폰 목록 보기
     */

    @GetMapping("coupon/list")
    public String myCouponList() {
        return "pages/myPage/myCouponList";
    }

}
