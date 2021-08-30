package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {


}
