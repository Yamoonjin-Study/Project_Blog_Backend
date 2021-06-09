package com.woonjin.blog.application;

import com.woonjin.blog.application.dto.CreateBlogRequest;
import com.woonjin.blog.application.dto.UpdateBlogRequest;
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

    public BlogService(BlogRepository blogRepository, HttpSession session){
        this.blogRepository = blogRepository;
        this.session = session;
    }

    @Transactional
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
        return createBlogRequest.getNickname()+"님의 "+createBlogRequest.getBlogname()+" 블로그가 생성되었습니다.";
    }

    @Transactional
    public String updateBlog(UpdateBlogRequest updateBlogRequest){
        User user = (User) session.getAttribute("user");

        Blog updateBlog = (Blog) blogRepository.findByUser_Id(user.getId());

        updateBlog.setBlogname(updateBlogRequest.getBlogname());
        updateBlog.setNickname(updateBlogRequest.getNickname());
        updateBlog.setIcon(updateBlogRequest.getIcon());
        updateBlog.setInfo(updateBlogRequest.getInfo());

        blogRepository.save(updateBlog);

        return updateBlogRequest.getBlogname()+" 블로그 정보가 수정되었습니다.";
    }

    @Transactional
    public String activateBlog(){
        User user = (User) session.getAttribute("user");
        Blog activateBlog = (Blog) blogRepository.findByUser_Id(user.getId());
        activateBlog.setStatus(Blog.Status.ACTIVE);
        blogRepository.save(activateBlog);
        return activateBlog.getBlogname()+" 블로그가 활성화 되었습니다.";
    }

    @Transactional
    public String inactivateBlog(){
        User user = (User) session.getAttribute("user");
        Blog activateBlog = (Blog) blogRepository.findByUser_Id(user.getId());
        activateBlog.setStatus(Blog.Status.INACTIVE);
        blogRepository.save(activateBlog);
        return activateBlog.getBlogname()+" 블로그가 비활성화 되었습니다.";
    }

    @Transactional
    public String deleteBlog(){
        User user = (User) session.getAttribute("user");
        Blog blog = (Blog) blogRepository.findByUser_Id(user.getId());
        blogRepository.delete(blog);
        return blog.getBlogname()+" 블로그가 삭제되었습니다.";
    }
}
