package com.woonjin.blog.web;

import com.woonjin.blog.application.BlogService;
import com.woonjin.blog.application.dto.CreateBlogRequest;
import com.woonjin.blog.application.dto.UpdateBlogRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"2. Blog Service"})
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }

    @PostMapping("/create-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public String 블로그생성(@RequestBody CreateBlogRequest createBlogRequest){
        return blogService.createBlog(createBlogRequest);
    }

    @PostMapping("update-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public String 블로그정보수정(@RequestBody UpdateBlogRequest updateBlogRequest){
        return blogService.updateBlog(updateBlogRequest);
    }

    @PostMapping("activate-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public String 블로그활성화(){
        return blogService.activateBlog();
    }

    @PostMapping("inactivate-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public String 블로그비활성화(){
        return blogService.inactivateBlog();
    }

    @PostMapping("delete-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public String 블로그삭제(){
        return blogService.deleteBlog();
    }
}
