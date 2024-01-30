package com.faruk.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faruk.coupon.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
