package com.woonjin.blog.domain.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.woonjin.blog.domain.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer>{
	Blog findByUser_Id(int id);
}
