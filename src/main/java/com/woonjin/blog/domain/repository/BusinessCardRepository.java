package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.BusinessCard;
import com.woonjin.blog.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessCardRepository extends JpaRepository<BusinessCard, Integer> {

    BusinessCard findByUser(User user);
}
