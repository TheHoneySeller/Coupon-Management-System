package com.faruk.coupon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.faruk.coupon.model.Coupon;
import com.faruk.coupon.repository.CouponRepository;

@Service
public class CouponService {
    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public List<Coupon> bulkUpload(List<Coupon> coupons) throws Exception{
        return couponRepository.saveAll(coupons);
    }


    
}
