package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Profile, Integer> {

}
