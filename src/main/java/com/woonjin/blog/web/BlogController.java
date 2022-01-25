package com.woonjin.blog.web;

import com.woonjin.blog.application.dto.request.WriteGuestBookRequest;
import com.woonjin.blog.application.dto.response.ActivateBlogResponse;
import com.woonjin.blog.application.dto.response.FollowingResponse;
import com.woonjin.blog.application.dto.response.BlogCheckResponse;
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
import com.woonjin.blog.domain.entity.Follow;
import io.swagger.annotations.Api;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api(tags = {"2. Blog Service"})
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blog-search/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public SearchBlogResponse SearchBlog(@PathVariable String name) {
        return this.blogService.searchBlog(name);
    }

    @GetMapping("/blog/myBlog")
    @ResponseStatus(value = HttpStatus.OK)
    public BlogCheckResponse findBlogName() {
        return this.blogService.checkBlogUser();
    }

    @GetMapping("/blog/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public ShowBlogResponse ShowBlog(@PathVariable String name) {

        //해당 블로그의 방문자에 기록 저장
        this.blogService.addVisitors(name);

        return this.blogService.showBlog(name);
    }

    @PostMapping("/create-blog")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CreateBlogResponse CreateBlog(
        @RequestBody CreateBlogRequest createBlogRequest
    ) {
        return blogService.createBlog(createBlogRequest);
    }

    @PostMapping("/save-file")
    public String SaveFile(
        @RequestPart(value = "iconImage") MultipartFile icon,
        @RequestPart(value = "logoImage") MultipartFile logo
    ) throws IOException {
        return this.blogService.saveFile(icon, logo);
    }

    @PostMapping("/save-icon-file")
    public String SaveIconFile(
        @RequestPart(value = "iconImage") MultipartFile icon
    ) throws IOException {
        return this.blogService.saveIconFile(icon);
    }

    @PostMapping("/save-logo-file")
    public String SaveLogoFile(
        @RequestPart(value = "logoImage") MultipartFile logo
    ) throws IOException {
        return this.blogService.saveLogoFile(logo);
    }

    @PutMapping("/update-blog-info")
    @ResponseStatus(value = HttpStatus.OK)
    public UpdateBlogResponse UpdateBlogInfo(@RequestBody UpdateBlogRequest updateBlogRequest) {
        return blogService.updateBlogInfo(updateBlogRequest);
    }

    @PutMapping("/update-blog-design")
    @ResponseStatus(value = HttpStatus.OK)
    public UpdateBlogResponse UpdateBlogDesign(@RequestBody UpdateBlogRequest updateBlogRequest) {
        return blogService.updateBlogDesign(updateBlogRequest);
    }

    @PutMapping("/activate-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public ActivateBlogResponse ActivateBlog() {
        return blogService.activateBlog();
    }

    @PutMapping("/inactivate-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public InactivateBlogResponse InactivateBlog() {
        return blogService.inactivateBlog();
    }

    @DeleteMapping("/delete-blog")
    @ResponseStatus(value = HttpStatus.OK)
    public DeleteBlogResponse DeleteBlog() {
        return blogService.deleteBlog();
    }

    @GetMapping("/blog/{name}/visitors")
    @ResponseStatus(value = HttpStatus.OK)
    public ShowVisitorsResponse ShowVisitors(@PathVariable String name) {
        return blogService.showVisitors(name);
    }

    @GetMapping("/blog/{name}/guestbook")
    @ResponseStatus(value = HttpStatus.OK)
    public GuestBookListResponse ShowGuestBooks(@PathVariable String name) {
        return blogService.showGuestBook(name);
    }

    @PostMapping("/blog/{name}/guestbook/write")
    @ResponseStatus(value = HttpStatus.OK)
    public WriteGuestBookResponse WriteGuestBook(@PathVariable String name,
        @RequestBody WriteGuestBookRequest writeGuestBookRequest) {
        return blogService.writeGuestBook(name, writeGuestBookRequest);
    }

    @DeleteMapping("/blog/guestbook/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public WriteGuestBookResponse DeleteGuestBook(@PathVariable int id) {
        return blogService.deleteGuestBook(id);
    }

    @GetMapping("/blog/following/{blog_name}")
    public FollowingResponse Following(@PathVariable String blog_name) {
        return this.blogService.Following(blog_name);
    }

    @GetMapping("/blog/showFollowingList/{blog_name}")
    public List<Follow> ShowFollowingList(@PathVariable String blog_name) {
        return this.blogService.showFollowingList(blog_name);
    }

    @GetMapping("/blog/showFollowerList/{blog_name}")
    public List<Follow> ShowFollowerList(@PathVariable String blog_name) {
        return this.blogService.showFollowerList(blog_name);
    }
}
