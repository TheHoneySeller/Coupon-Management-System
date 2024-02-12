package com.faruk.coupon.util.timerstrategy;

import com.faruk.coupon.util.observer.Killable;

public class BasicCouponTimerStrategy implements CouponTimerStrategy{

    @Override
    public boolean isAddable() {
        return true;
    }

    @Override
    public void couponAdded() {
        // do nothing  
    }

    @Override
    public void addKillable(Killable k) {
        // no timing related restrictions to keep track of kill the task now.
        k.kill();
    }

    
    
}
