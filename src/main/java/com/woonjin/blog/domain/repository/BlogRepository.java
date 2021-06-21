package com.woonjin.blog.domain.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.woonjin.blog.domain.entity.Blog;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    Blog findById(int id);

    Blog findByUser_Id(int id);

    Blog findByName(String name);

    @Query(value = "select name from blogs where name like %?1%", nativeQuery = true)
    List<String> searchBlog(String name);
}
