package com.woonjin.blog.application;

import com.woonjin.blog.application.dto.CreateBlogRequest;
import com.woonjin.blog.domain.entity.Blog;
import com.woonjin.blog.domain.entity.User;
import com.woonjin.blog.domain.repository.BlogRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class BlogCreationService {
    private final BlogRepository blogRepository;
    private final HttpSession session;

    public BlogCreationService(BlogRepository blogRepository, HttpSession session){
        this.blogRepository = blogRepository;
        this.session = session;
    }

    public String createBlog(CreateBlogRequest createBlogRequest){
        Blog createBlog = this.blogRepository.save(
                Blog.of(
                        createBlogRequest.getBlogname(),
                        createBlogRequest.getNickname(),
                        createBlogRequest.getInfo(),
                        createBlogRequest.getIcon(),
                        Blog.Status.ACTIVE,
                        (User) session.getAttribute("user")
                )
        );
        return "블로그가 생성되었습니다.";
    }
}
