package com.faruk.coupon.service;

import org.springframework.stereotype.Service;

import com.faruk.coupon.repository.CouponRepository;

@Service
public class CouponService {
    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    
    
}
