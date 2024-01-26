package com.faruk.coupon.model;

import java.time.OffsetDateTime;

import com.faruk.coupon.constants_enums.ConstantsEnums.CouponType;
import com.faruk.coupon.constants_enums.ConstantsEnums.DiscountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Coupon {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String code;
    private CouponType type;

    private DiscountType discountType;
    
    private Double discountAmount;
    private Double discountPercentage;

    private OffsetDateTime expirationTime;

    private Integer numberOfUsagesLeft;

    public Coupon(Long id, String code, CouponType type, DiscountType discountType, Double discountAmount,
            Double discountPercentage, OffsetDateTime expirationTime, Integer numberOfUsagesLeft) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.discountPercentage = discountPercentage;
        this.expirationTime = expirationTime;
        this.numberOfUsagesLeft = numberOfUsagesLeft;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CouponType getType() {
        return type;
    }

    public void setType(CouponType type) {
        this.type = type;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public OffsetDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(OffsetDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Integer getNumberOfUsagesLeft() {
        return numberOfUsagesLeft;
    }

    public void setNumberOfUsagesLeft(Integer numberOfUsagesLeft) {
        this.numberOfUsagesLeft = numberOfUsagesLeft;
    }

    

    
}
