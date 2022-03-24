package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.Blog;
import com.woonjin.blog.domain.entity.Follow;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Integer> {

    //계정주인이 팔로우한 블로그 리스트
    List<Follow> findByBlogger(Blog blogger);

    //나를 팔로우 하는 계정 리스트
    List<Follow> findByFollowingBlog(Blog followingBlog);

    //내가 해당블로그를 팔로우했는지 확인
    Follow findByBloggerAndFollowingBlog(Blog blogger, Blog followingBlog);
}
