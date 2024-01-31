package com.faruk.coupon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.faruk.coupon.constants_enums.ConstantsEnums;
import com.faruk.coupon.constants_enums.ConstantsEnums.CouponType;
import com.faruk.coupon.model.Coupon;
import com.faruk.coupon.model.User;
import com.faruk.coupon.repository.CouponRepository;
import com.faruk.coupon.repository.UserRepository;

@Service
public class CouponService {
    private final CouponRepository couponRepository;
    private final UserRepository userRepository;

    public CouponService(CouponRepository couponRepository, UserRepository userRepository) {
        this.couponRepository = couponRepository;
        this.userRepository = userRepository;
    }

    public List<Coupon> bulkUpload(List<Coupon> coupons) throws Exception{
        return couponRepository.saveAll(coupons);
    }

    public Pair<HttpStatus, String> requestCoupon(Long userId, CouponType couponType) {
        
        //get user
        if (userId == null) {
            return Pair.of(HttpStatus.BAD_REQUEST, "userId cannot be null!");
        }
        
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return Pair.of(HttpStatus.BAD_REQUEST, "User with userId: " + userId + " does not exist.");
        } 
        User user = optionalUser.get();

        //get coupon if exists
        if (couponType == null) {
            couponType = ConstantsEnums.DEFAULT_COUPON_TYPE;
        }

        Coupon coupon = couponRepository.findDistinctByCouponType(couponType);
        if (coupon == null) {
            return Pair.of(HttpStatus.NOT_FOUND, "Cannot find a coupon with coupon type: " + couponType);
        }

        user.addCoupon(coupon);
        userRepository.save(user);
        return Pair.of(HttpStatus.OK, "Coupon added.");
        
    }


    
}
