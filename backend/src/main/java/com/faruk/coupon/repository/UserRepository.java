package com.faruk.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faruk.coupon.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
