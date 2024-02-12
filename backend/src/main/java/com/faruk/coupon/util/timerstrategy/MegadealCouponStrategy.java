package com.faruk.coupon.util.timerstrategy;


import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.faruk.coupon.constants_enums.ConstantsEnums;
import com.faruk.coupon.util.observer.Killable;

public class MegadealCouponStrategy implements CouponTimerStrategy {

    private final Set<OffsetDateTime> addTimes = new HashSet<OffsetDateTime>(); // time stamps containing the times a coupon was added. Automatically removed after a time window.
    private Killable couponTaskToKill = null;
    private Timer timer = new Timer();

    public MegadealCouponStrategy() {
        
    }

    @Override
    public boolean isAddable() {
        return addTimes.size() < 10;
    }

    @Override
    public void couponAdded() {
        OffsetDateTime addTime = OffsetDateTime.now();
        TimerTask task = new TimerTask() {
            public void run() {
                // a fixed delay has passed. Remove the time stamp and send the kill signal if there are no time stamps left.
                addTimes.remove(addTime);
                
                if (addTimes.isEmpty()) {
                    couponTaskToKill.kill();
                }
            }
        };

        addTimes.add(addTime);
        timer.schedule(task, ConstantsEnums.MEGADEAL_TIME_WINDOW);

    }

    @Override
    public void addKillable(Killable k) {
        if (couponTaskToKill == null) {
            couponTaskToKill = k;
        }
    }
    
}
