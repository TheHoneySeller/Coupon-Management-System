package com.faruk.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faruk.coupon.constants_enums.ConstantsEnums.CouponType;
import com.faruk.coupon.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{

    Coupon findDistinctByType(CouponType couponType);
    
}
