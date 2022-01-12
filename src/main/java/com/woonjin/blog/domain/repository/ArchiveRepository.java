package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Archive;
import com.woonjin.blog.domain.entity.Blog;
import com.woonjin.blog.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveRepository extends JpaRepository<Archive, Integer> {

    Archive findByBlog(Blog blog);

    Archive findById(int id);
}
