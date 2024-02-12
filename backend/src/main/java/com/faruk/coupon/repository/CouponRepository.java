package com.faruk.coupon.repository;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.faruk.coupon.constants_enums.ConstantsEnums.CouponType;
import com.faruk.coupon.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{

    //return a usable coupon if it exists.
    Coupon findDistinctByTypeAndNumberOfUsagesLeftGreaterThanAndExpiresOnGreaterThanAndCouponUserIsNull(CouponType couponType, Integer min, OffsetDateTime now);
    
    @Query("SELECT SUM(c.numberOfUsagesLeft) FROM Coupon c")
    Long getTotalUsagesLeft();
}
