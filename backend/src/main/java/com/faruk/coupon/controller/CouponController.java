package com.faruk.coupon.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.faruk.coupon.constants_enums.ConstantsEnums;
import com.faruk.coupon.model.Coupon;
import com.faruk.coupon.service.CouponService;

@Controller
public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/bulkcouponupload")
    public ResponseEntity<List<Coupon>> bulkCouponUpload(@RequestBody List<Coupon> coupons) {
        if (coupons == null || coupons.size() > ConstantsEnums.MAX_COUPON_UPLOAD_NUMBER) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            //System.out.println( "\n\n\n\n\n\n\n\n::>>>>>>" + Arrays.toString(coupons.toArray()));

            try {
                List<Coupon> returnedCoupons = couponService.bulkUpload(coupons);
                return ResponseEntity.ok().body(returnedCoupons); 
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body(null);
            }

            
        }
    }
    
    
}
