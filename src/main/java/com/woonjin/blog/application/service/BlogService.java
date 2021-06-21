package com.woonjin.blog.application.service;

import com.woonjin.blog.application.dto.request.CreateBlogRequest;
import com.woonjin.blog.application.dto.request.UpdateBlogRequest;
import com.woonjin.blog.application.dto.response.ActivateBlogResponse;
import com.woonjin.blog.application.dto.response.CreateBlogResponse;
import com.woonjin.blog.application.dto.response.DeleteBlogResponse;
import com.woonjin.blog.application.dto.response.InactivateBlogResponse;
import com.woonjin.blog.application.dto.response.ShowBlogResponse;
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
    public ShowBlogResponse showBlog(String blog_name){
        if(blogRepository.findByName(blog_name) != null){
            return ShowBlogResponse.of("Look Up Success", blogRepository.findByName(blog_name));
        }else{
            return ShowBlogResponse.of("Look Up Fail", blogRepository.findByName(blog_name));
        }
    }

    @Transactional
    public CreateBlogResponse createBlog(CreateBlogRequest createBlogRequest) {
        Blog createBlog = this.blogRepository.save(
            Blog.of(
                createBlogRequest.getName(),
                createBlogRequest.getNick_name(),
                createBlogRequest.getInfo(),
                createBlogRequest.getIcon(),
                Blog.Status.ACTIVE,
                createBlogRequest.getLogo_image(),
                createBlogRequest.getDesign_form(),
                createBlogRequest.getCategory(),
                (User) session.getAttribute("user")
            )
        );
        return CreateBlogResponse.of("Create Blog Success", createBlog);
    }

    @Transactional
    public UpdateBlogResponse updateBlog(UpdateBlogRequest updateBlogRequest) {
        User user = (User) session.getAttribute("user");

        Blog updateBlog = (Blog) blogRepository.findByUser_Id(user.getId());

        updateBlog.setName(updateBlogRequest.getName());
        updateBlog.setNick_name(updateBlogRequest.getNick_name());
        updateBlog.setIcon(updateBlogRequest.getIcon());
        updateBlog.setInfo(updateBlogRequest.getInfo());
        updateBlog.setLogo_image(updateBlogRequest.getLogo_image());
        updateBlog.setDesign_form(updateBlogRequest.getDesign_form());
        updateBlog.setCategory(updateBlogRequest.getCategory());

        blogRepository.save(updateBlog);

        return UpdateBlogResponse.of("Update Blog Success", updateBlogRequest);
    }

    @Transactional
    public ActivateBlogResponse activateBlog() {
        User user = (User) session.getAttribute("user");
        Blog activateBlog = (Blog) blogRepository.findByUser_Id(user.getId());
        activateBlog.activate(activateBlog);
        blogRepository.save(activateBlog);
        return ActivateBlogResponse.of("Activation Success", activateBlog);
    }

    @Transactional
    public InactivateBlogResponse inactivateBlog() {
        User user = (User) session.getAttribute("user");
        Blog inactivateBlog = (Blog) blogRepository.findByUser_Id(user.getId());
        inactivateBlog.inactivate(inactivateBlog);
        blogRepository.save(inactivateBlog);
        return InactivateBlogResponse.of("Inactivation Success", inactivateBlog);
    }

    @Transactional
    public DeleteBlogResponse deleteBlog() {
        User user = (User) session.getAttribute("user");
        Blog blog = (Blog) blogRepository.findByUser_Id(user.getId());
        blogRepository.delete(blog);
        return DeleteBlogResponse.of("Delete Blog Success", blog);
    }
}
