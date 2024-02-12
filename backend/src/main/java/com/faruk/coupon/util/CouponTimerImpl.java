package com.faruk.coupon.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.faruk.coupon.constants_enums.ConstantsEnums;
import com.faruk.coupon.model.Coupon;
import com.faruk.coupon.model.User;
import com.faruk.coupon.util.observer.Killable;
import com.faruk.coupon.util.timerstrategy.BasicCouponTimerStrategy;
import com.faruk.coupon.util.timerstrategy.CouponTimerStrategy;
import com.faruk.coupon.util.timerstrategy.MegadealCouponStrategy;

@Component
public class CouponTimerImpl implements CouponTimer {

    private final Map<Long, CouponTimerTask> tasks = new HashMap<Long, CouponTimerTask>();

    @Override
    public boolean addCoupon(User user, Coupon coupon) {
        Long key = user.getId();
        
        if (tasks.containsKey(key)) {
            return tasks.get(key).addCoupon(coupon);
        } else {
            CouponTimerStrategy strategy;
            CouponTimerTask task;
            if (coupon.getType() == ConstantsEnums.CouponType.MEGADEAL) {
                strategy = new MegadealCouponStrategy();
                task = new CouponTimerTask(strategy, user);
                tasks.put(key, task);
                strategy.addKillable(task); // task will be killed (it will stop counting) after a given delay.
                return task.addCoupon(coupon);
            } else {
                strategy = new BasicCouponTimerStrategy();
                task = new CouponTimerTask(strategy, user);
                tasks.put(key, task);
                boolean success = task.addCoupon(coupon);
                strategy.addKillable(task); // kill the basic task. No need to keep track of anything.
                return success;
            }
        }
    }
    

    private class CouponTimerTask implements Killable{
        User user;
        CouponTimerStrategy strategy;

        public CouponTimerTask(CouponTimerStrategy strategy, User user) {
            this.strategy = strategy;
            this.user = user;
        }
        
        boolean addCoupon(Coupon coupon) {
            if (strategy.isAddable()) {
                strategy.couponAdded();
                user.addCoupon(coupon);
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void kill() {
            tasks.remove(user.getId());
            //suicide by garbage collection
        }
    
    }
}
