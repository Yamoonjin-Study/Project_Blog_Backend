package com.woonjin.blog.domain.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.woonjin.blog.domain.entity.Blog;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    Blog findById(int id);

    Blog findByUser_Id(int id);

    Blog findByBlogName(String blog_name);

    @Query(value = "select blog_name from blogs order by create_date DESC", nativeQuery = true)
    List<String> showBloggersOrderBySignUpDate();

    @Query(value = "select blog_name from blogs where blog_name like %?1% and status like 'ACTIVE'", nativeQuery = true)
    List<String> searchBlog(String blog_name);
    
    //List<String> findNameByNameContaining(String name);
}
