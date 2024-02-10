package com.faruk.coupon.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.faruk.coupon.model.Coupon;
import com.faruk.coupon.model.User;
import com.faruk.coupon.repository.CouponRepository;
import com.faruk.coupon.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CouponRepository couponRepository;

    Logger logger = LoggerFactory.getLogger(UserRepository.class);
    

    public UserService(UserRepository userRepository, CouponRepository couponRepository) {
        this.userRepository = userRepository;
        this.couponRepository = couponRepository;
    }

    public User postUser(User user) throws Exception {
        return  userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<String> redeemCoupon(Long userId, Long couponId) {
        String msg;
        
        if (userId == null || couponId == null) {
            msg = "userId and couponId must be non null.";
            logger.warn(msg);
            return ResponseEntity.badRequest().body(msg);
        }

        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
        if (optionalUser.isEmpty() || optionalCoupon.isEmpty()) {
            msg = "user or coupon cannot be found.";
            logger.warn(msg);
            return ResponseEntity.badRequest().body(msg);
        }

        User user = optionalUser.get();
        Coupon coupon = optionalCoupon.get();

        if(isNotValid(coupon, user)) {
            msg = "this coupon and user combination is not valid.";
            logger.warn(msg);
            return ResponseEntity.badRequest().body(msg);
        }

        // todo: implement time megadeal restrictions
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("implement me booooo");
    }

    private boolean isNotValid(Coupon coupon, User user) {
        return !isValid(coupon, user);
    }

    private boolean isValid(Coupon coupon, User user) {
        OffsetDateTime now = OffsetDateTime.now();

        return  coupon != null && 
                coupon.getCouponUser() == user && 
                coupon.getNumberOfUsagesLeft() > 0 && 
                now.isBefore(coupon.getExpiresOn());
    }
    
}
