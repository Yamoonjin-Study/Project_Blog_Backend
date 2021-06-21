package com.woonjin.blog.web;

import com.woonjin.blog.application.dto.response.ActivateBlogResponse;
import com.woonjin.blog.application.dto.response.CreateBlogResponse;
import com.woonjin.blog.application.dto.response.DeleteBlogResponse;
import com.woonjin.blog.application.dto.response.InactivateBlogResponse;
import com.woonjin.blog.application.dto.response.ShowBlogResponse;
import com.woonjin.blog.application.dto.response.UpdateBlogResponse;
import com.woonjin.blog.application.service.BlogService;
import com.woonjin.blog.application.dto.request.CreateBlogRequest;
import com.woonjin.blog.application.dto.request.UpdateBlogRequest;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"2. Blog Service"})
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blog/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public ShowBlogResponse 블로그조회(@PathVariable String name) {
        return blogService.showBlog(name);
    }

    @PostMapping("/create-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public CreateBlogResponse 블로그생성(@RequestBody CreateBlogRequest createBlogRequest) {
        return blogService.createBlog(createBlogRequest);
    }

    @PostMapping("update-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public UpdateBlogResponse 블로그정보수정(@RequestBody UpdateBlogRequest updateBlogRequest) {
        return blogService.updateBlog(updateBlogRequest);
    }

    @PostMapping("activate-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public ActivateBlogResponse 블로그활성화() {
        return blogService.activateBlog();
    }

    @PostMapping("inactivate-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public InactivateBlogResponse 블로그비활성화() {
        return blogService.inactivateBlog();
    }

    @PostMapping("delete-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public DeleteBlogResponse 블로그삭제() {
        return blogService.deleteBlog();
    }


}
