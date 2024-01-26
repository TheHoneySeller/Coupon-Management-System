package com.faruk.coupon.controller;

import org.springframework.stereotype.Controller;

import com.faruk.coupon.service.CouponService;

@Controller
public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    
}
