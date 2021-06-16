package com.woonjin.blog.application.service;

import com.woonjin.blog.application.dto.request.CreateBlogRequest;
import com.woonjin.blog.application.dto.request.UpdateBlogRequest;
import com.woonjin.blog.application.dto.response.ActivateBlogResponse;
import com.woonjin.blog.application.dto.response.CreateBlogResponse;
import com.woonjin.blog.application.dto.response.DeleteBlogResponse;
import com.woonjin.blog.application.dto.response.InactivateBlogResponse;
import com.woonjin.blog.application.dto.response.UpdateBlogResponse;
import com.woonjin.blog.domain.entity.Blog;
import com.woonjin.blog.domain.entity.User;
import com.woonjin.blog.domain.repository.BlogRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final HttpSession session;

    public BlogService(BlogRepository blogRepository, HttpSession session) {
        this.blogRepository = blogRepository;
        this.session = session;
    }

    @Transactional
    public CreateBlogResponse createBlog(CreateBlogRequest createBlogRequest) {
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
        return CreateBlogResponse.of(createBlogRequest.getNickname() + "님, 회원님의 " + createBlogRequest.getBlogname()
            + " 블로그가 생성되었습니다.", createBlog);
    }

    @Transactional
    public UpdateBlogResponse updateBlog(UpdateBlogRequest updateBlogRequest) {
        User user = (User) session.getAttribute("user");

        Blog updateBlog = (Blog) blogRepository.findByUser_Id(user.getId());

        updateBlog.setBlogname(updateBlogRequest.getBlogname());
        updateBlog.setNickname(updateBlogRequest.getNickname());
        updateBlog.setIcon(updateBlogRequest.getIcon());
        updateBlog.setInfo(updateBlogRequest.getInfo());

        blogRepository.save(updateBlog);

        return UpdateBlogResponse.of("updateblog success", updateBlogRequest);
    }

    @Transactional
    public ActivateBlogResponse activateBlog() {
        User user = (User) session.getAttribute("user");
        Blog activateBlog = (Blog) blogRepository.findByUser_Id(user.getId());
        activateBlog.activate(activateBlog);
        blogRepository.save(activateBlog);
        return ActivateBlogResponse.of("activation success", activateBlog);
    }

    @Transactional
    public InactivateBlogResponse inactivateBlog() {
        User user = (User) session.getAttribute("user");
        Blog inactivateBlog = (Blog) blogRepository.findByUser_Id(user.getId());
        inactivateBlog.inactivate(inactivateBlog);
        blogRepository.save(inactivateBlog);
        return InactivateBlogResponse.of("inactivation success", inactivateBlog);
    }

    @Transactional
    public DeleteBlogResponse deleteBlog() {
        User user = (User) session.getAttribute("user");
        Blog blog = (Blog) blogRepository.findByUser_Id(user.getId());
        blogRepository.delete(blog);
        return DeleteBlogResponse.of("deleteblog success", blog);
    }
}
