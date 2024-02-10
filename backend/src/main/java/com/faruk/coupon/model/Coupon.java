package com.faruk.coupon.model;

import java.lang.reflect.Field;
import java.time.OffsetDateTime;

import com.faruk.coupon.constants_enums.ConstantsEnums.CouponType;
import com.faruk.coupon.constants_enums.ConstantsEnums.DiscountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;



@Entity
public class Coupon {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Pattern(regexp = "^[A-Z0-9]{3,10}$") //alphanumeric string between 3 and 10 characters.
    @Column(unique=true)
    private String code;
    private CouponType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;
    
    @NotNull
    @Min(0)
    private Double discountAmount;

    @NotNull
    @Min(0)
    @Max(100)
    private Double discountPercentage;

    private OffsetDateTime expiresOn;

    @Min(0)
    private Integer numberOfUsagesLeft;

    @ManyToOne
    private User couponUser;

    public Coupon( String code, CouponType type, DiscountType discountType, Double discountAmount,
            Double discountPercentage, OffsetDateTime expirationTime, Integer numberOfUsagesLeft) {
        this.code = code;
        this.type = type;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.discountPercentage = discountPercentage;
        this.expiresOn = expirationTime;
        this.numberOfUsagesLeft = numberOfUsagesLeft;
    }

    public Coupon() {
    }

    

    public User getCouponUser() {
        return couponUser;
    }

    public void setCouponUser(User user) {
        this.couponUser = user;
    }

    public Long getId() {
        return id;
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

    public OffsetDateTime getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(OffsetDateTime expirationTime) {
        this.expiresOn = expirationTime;
    }

    public Integer getNumberOfUsagesLeft() {
        return numberOfUsagesLeft;
    }

    public void setNumberOfUsagesLeft(Integer numberOfUsagesLeft) {
        this.numberOfUsagesLeft = numberOfUsagesLeft;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append(this.getClass().getName());
        result.append(" Object {");
        result.append(newLine);

        // determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        // print field names paired with their values
        for (Field field : fields) {
            result.append("  ");
            try {
                result.append(field.getName());
                result.append(": ");
                // requires access to private field:
                result.append(field.get(this));
            } catch (IllegalAccessException ex) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }

    public void useOnceIfAvailable() {
        if (numberOfUsagesLeft > 0) {
            numberOfUsagesLeft--;
        }
    }

    
}
