package com.faruk.coupon.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.faruk.coupon.constants_enums.ConstantsEnums;
import com.faruk.coupon.constants_enums.ConstantsEnums.CouponType;
import com.faruk.coupon.model.Coupon;
import com.faruk.coupon.service.CouponService;


@Controller
public class CouponController {
    private final CouponService couponService;
    //private final LanguageProvider language; // to do: implement and use :D
    private Logger logger = LoggerFactory.getLogger(CouponController.class);

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/bulkcouponupload")
    public ResponseEntity<String> bulkCouponUpload(@RequestBody List<Coupon> coupons) {
        logger.info("info mf");
        if (coupons == null || coupons.size() > ConstantsEnums.MAX_COUPON_UPLOAD_NUMBER) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coupons is null or there are too many coupons to upload. Max: " + ConstantsEnums.MAX_COUPON_UPLOAD_NUMBER);
        } else {
            //System.out.println( "\n\n\n\n\n\n\n\n::>>>>>>" + Arrays.toString(coupons.toArray()));

            try {
                List<Coupon> returnedCoupons = couponService.bulkUpload(coupons);
                return ResponseEntity.ok().body(returnedCoupons.toString()); 
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("Exception while trying to insert coupons: " + e.getMessage());
            }

            
        }
    }

    @PutMapping("/requestcoupon")
    public ResponseEntity<String> requestCoupon( @RequestParam Long userId, @RequestParam CouponType couponType) {
        Pair<HttpStatus, String> pair = couponService.requestCoupon(userId, couponType);

        return ResponseEntity.status(pair.getFirst()).body(pair.getSecond());
    }
    
    
}
