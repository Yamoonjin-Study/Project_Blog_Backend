package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Portfolio;
import com.woonjin.blog.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
    Portfolio findByUser(User user);
}
