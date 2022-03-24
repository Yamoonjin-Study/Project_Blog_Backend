package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import com.woonjin.blog.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    User findByEmail(String email);

    User findByBlog(Blog blog);
}
