package com.woonjin.blog.web;

import com.woonjin.blog.application.dto.request.WriteGuestBookRequest;
import com.woonjin.blog.application.dto.response.ActivateBlogResponse;
import com.woonjin.blog.application.dto.response.CreateBlogResponse;
import com.woonjin.blog.application.dto.response.DeleteBlogResponse;
import com.woonjin.blog.application.dto.response.GuestBookListResponse;
import com.woonjin.blog.application.dto.response.InactivateBlogResponse;
import com.woonjin.blog.application.dto.response.SearchBlogResponse;
import com.woonjin.blog.application.dto.response.ShowBlogResponse;
import com.woonjin.blog.application.dto.response.ShowVisitorsResponse;
import com.woonjin.blog.application.dto.response.UpdateBlogResponse;
import com.woonjin.blog.application.dto.response.WriteGuestBookResponse;
import com.woonjin.blog.application.service.BlogService;
import com.woonjin.blog.application.dto.request.CreateBlogRequest;
import com.woonjin.blog.application.dto.request.UpdateBlogRequest;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/blog-search/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public SearchBlogResponse 블로그검색(@PathVariable String name) {
        return blogService.searchBlog(name);
    }

    @GetMapping("/blog/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public ShowBlogResponse 블로그조회(@PathVariable String name) {

        //해당 블로그의 방문자에 기록 저장
        blogService.addVisitors(name);

        return blogService.showBlog(name);
    }

    @PostMapping("/create-blog")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CreateBlogResponse 블로그생성(
        @RequestBody CreateBlogRequest createBlogRequest
    ) {
        return blogService.createBlog(createBlogRequest);
    }

    @PutMapping("update-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public UpdateBlogResponse 블로그정보수정(@RequestBody UpdateBlogRequest updateBlogRequest) {
        return blogService.updateBlog(updateBlogRequest);
    }

    @PutMapping("activate-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public ActivateBlogResponse 블로그활성화() {
        return blogService.activateBlog();
    }

    @PutMapping("inactivate-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public InactivateBlogResponse 블로그비활성화() {
        return blogService.inactivateBlog();
    }

    @DeleteMapping("delete-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public DeleteBlogResponse 블로그삭제() {
        return blogService.deleteBlog();
    }

    @GetMapping("blog/{name}/visitors")
    @ResponseStatus(value = HttpStatus.OK)
    public ShowVisitorsResponse 방문자확인(@PathVariable String name){
        return blogService.showVisitors(name);
    }

    @GetMapping("blog/{name}/guestbook")
    @ResponseStatus(value = HttpStatus.OK)
    public GuestBookListResponse 방명록조회(@PathVariable String name){
        return blogService.showGuestBook(name);
    }

    @PostMapping("blog/{name}/guestbook/write")
    @ResponseStatus(value = HttpStatus.OK)
    public WriteGuestBookResponse 방명록작성(@PathVariable String name, @RequestBody WriteGuestBookRequest writeGuestBookRequest){
        return blogService.writeGuestBook(name, writeGuestBookRequest);
    }
}
