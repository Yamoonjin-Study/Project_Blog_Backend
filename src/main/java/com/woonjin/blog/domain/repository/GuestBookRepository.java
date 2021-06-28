package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Blog;
import com.woonjin.blog.domain.entity.GuestBook;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestBookRepository extends JpaRepository<GuestBook, Integer> {

    List<GuestBook> findByBlog(Blog blog);
}
