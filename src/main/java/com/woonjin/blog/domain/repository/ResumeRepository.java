package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Resume;
import com.woonjin.blog.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    Resume findByUser(User user);
}
