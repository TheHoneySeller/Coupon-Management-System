package com.faruk.coupon.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "`user`")
public class User {
    
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "couponUser")
    private List<Coupon> coupons;

    public User() {
    }

    public User(Long id, String name, List<Coupon> coupons) {
        this.id = id;
        this.name = name;
        this.coupons = coupons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    
    

}
