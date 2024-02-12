package com.faruk.coupon.constants_enums;

public class ConstantsEnums {
    public static enum CouponType {
        FREE, STANDARD, MEGADEAL
    }

    public static enum DiscountType {
        FLAT, PERCENTAGE
    }

    public static final double MAX_DISCOUNT_PERCENTAGE = 100;
    public static final double MIN_DISCOUNT_PERCENTAGE = 0;

    public static final int MAX_COUPON_UPLOAD_NUMBER = 10000;
    public static final CouponType DEFAULT_COUPON_TYPE = CouponType.STANDARD;

    public static final long MEGADEAL_TIME_WINDOW = 1000; // In miliseconds. One second.
    public static final int MAX_MEGADEAL_COUPONS_PER_TIME_WINDOW = 10;
}
