package com.woonjin.blog.web;

import com.woonjin.blog.application.BlogCreationService;
import com.woonjin.blog.application.dto.CreateBlogRequest;
import com.woonjin.blog.domain.entity.Blog;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"2. BlogCreation Service"})
public class BlogCreationController {

    private final BlogCreationService blogCreationService;

    public BlogCreationController(BlogCreationService blogCreationService){
        this.blogCreationService = blogCreationService;
    }

    @PostMapping("/create-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public String 블로그생성(@RequestBody CreateBlogRequest createBlogRequest){
        return blogCreationService.createBlog(createBlogRequest);
    }

    @PostMapping("update-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public String 블로그수정(){
        return "";
    }

    @PostMapping("delete-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public String 블로그삭제(){
        return "";
    }
}
