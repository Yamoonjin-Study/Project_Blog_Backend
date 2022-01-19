package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Archive;
import com.woonjin.blog.domain.entity.Blog;
import com.woonjin.blog.domain.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveRepository extends JpaRepository<Archive, Integer> {

    List<Archive> findByBlog(Blog blog);

    Archive findById(int id);
}
