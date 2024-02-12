package com.faruk.coupon.util;

import com.faruk.coupon.model.Coupon;
import com.faruk.coupon.model.User;

public interface CouponTimer {

    boolean addCoupon(User user, Coupon coupon);

    
}