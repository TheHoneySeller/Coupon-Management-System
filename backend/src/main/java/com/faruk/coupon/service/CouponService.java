package com.faruk.coupon.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    Logger logger = LoggerFactory.getLogger(CouponService.class);

    public CouponService(CouponRepository couponRepository, UserRepository userRepository) {
        this.couponRepository = couponRepository;
        this.userRepository = userRepository;

        
    }

    public List<Coupon> bulkUpload(List<Coupon> coupons) throws Exception{
        return couponRepository.saveAll(coupons);
    }

    public Pair<HttpStatus, String> requestCoupon(Long userId, CouponType couponType) {
        String msg;
        //get user
        if (userId == null) {
            msg = "userId cannot be null!";
            logger.warn(msg);
            return Pair.of(HttpStatus.BAD_REQUEST, msg);
        }
        
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            msg = "User with userId: " + userId + " does not exist.";
            logger.warn(msg);
            return Pair.of(HttpStatus.BAD_REQUEST, msg);
        } 
        User user = optionalUser.get();

        //get coupon if exists
        if (couponType == null) {
            couponType = ConstantsEnums.DEFAULT_COUPON_TYPE;
        }

        Coupon coupon = couponRepository.findDistinctByTypeAndNumberOfUsagesLeftGreaterThanAndExpiresOnGreaterThanAndUserIsNull(couponType, 0, OffsetDateTime.now());
        if (coupon == null) {
            msg = "Cannot find a coupon with coupon type: " + couponType + " which has any usages left and not already assigned to a user.";
            logger.warn(msg);
            return Pair.of(HttpStatus.NOT_FOUND, msg);
        }

        user.addCoupon(coupon);
        
        if (coupon.getNumberOfUsagesLeft() <= 0) {
            couponRepository.delete(coupon);
        }

        userRepository.save(user);

        msg = "User with userId: " + userId + " added coupon with coupon code: " + coupon.getCode() + " and coupon type: " + couponType;
        logger.info(msg);

        return Pair.of(HttpStatus.OK, msg);
        
    }


    
}
