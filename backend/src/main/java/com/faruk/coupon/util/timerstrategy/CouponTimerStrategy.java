package com.faruk.coupon.util.timerstrategy;

import com.faruk.coupon.util.observer.Killer;

public interface CouponTimerStrategy extends Killer{
    public boolean isAddable();
    public void couponAdded();
}
