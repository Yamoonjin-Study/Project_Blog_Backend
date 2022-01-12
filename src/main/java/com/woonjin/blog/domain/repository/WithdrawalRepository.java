package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Integer> {

}
