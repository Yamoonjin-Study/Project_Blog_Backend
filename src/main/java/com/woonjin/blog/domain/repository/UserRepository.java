package com.woonjin.blog.domain.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woonjin.blog.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
