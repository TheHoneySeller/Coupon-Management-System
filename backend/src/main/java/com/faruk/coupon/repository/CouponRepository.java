package com.faruk.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faruk.coupon.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>{
    
}
