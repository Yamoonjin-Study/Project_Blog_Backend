package com.woonjin.blog.application.service;

import com.woonjin.blog.application.dto.request.CreateBlogRequest;
import com.woonjin.blog.application.dto.request.UpdateBlogRequest;
import com.woonjin.blog.application.dto.request.WriteGuestBookRequest;
import com.woonjin.blog.application.dto.response.ActivateBlogResponse;
import com.woonjin.blog.application.dto.response.CreateBlogResponse;
import com.woonjin.blog.application.dto.response.DeleteBlogResponse;
import com.woonjin.blog.application.dto.response.InactivateBlogResponse;
import com.woonjin.blog.application.dto.response.SearchBlogResponse;
import com.woonjin.blog.application.dto.response.ShowBlogResponse;
import com.woonjin.blog.application.dto.response.ShowVisitorsResponse;
import com.woonjin.blog.application.dto.response.UpdateBlogResponse;
import com.woonjin.blog.application.dto.response.VisitorInfo;
import com.woonjin.blog.application.dto.response.WriteGuestBookResponse;
import com.woonjin.blog.domain.entity.Blog;
import com.woonjin.blog.domain.entity.GuestBook;
import com.woonjin.blog.domain.entity.User;
import com.woonjin.blog.domain.entity.Visitor;
import com.woonjin.blog.domain.repository.BlogRepository;
import com.woonjin.blog.domain.repository.GuestBookRepository;
import com.woonjin.blog.domain.repository.UserRepository;
import com.woonjin.blog.domain.repository.VisitorRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final VisitorRepository visitorRepository;
    private final GuestBookRepository guestBookRepository;
    private final UserRepository userRepository;
    private final HttpSession session;

    public BlogService(BlogRepository blogRepository, VisitorRepository visitorRepository,
        GuestBookRepository guestBookRepository, UserRepository userRepository,
        HttpSession session) {
        this.blogRepository = blogRepository;
        this.visitorRepository = visitorRepository;
        this.guestBookRepository = guestBookRepository;
        this.userRepository = userRepository;
        this.session = session;
    }

    @Transactional
    public ShowBlogResponse showBlog(String name) {
        if (blogRepository.findByName(name) != null) {
            return ShowBlogResponse.of("Look Up Success", blogRepository.findByName(name));
        } else {
            return ShowBlogResponse.of("No Result", blogRepository.findByName(name));
        }
    }

    @Transactional
    public SearchBlogResponse searchBlog(String name) {

        List<String> list = blogRepository.searchBlog(name);

        return SearchBlogResponse.of("Results of searching", list);
    }

    @Transactional
    public CreateBlogResponse createBlog(CreateBlogRequest createBlogRequest) {
        Blog createBlog = this.blogRepository.save(
            Blog.of(
                createBlogRequest.getName(),
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

    @Transactional
    public void addVisitors(String name) {
        Visitor addVisitor = new Visitor();

        addVisitor.setBlog(blogRepository.findByName(name));
        addVisitor.setUser((User) session.getAttribute("user"));

        visitorRepository.save(addVisitor);
    }

    @Transactional
    public ShowVisitorsResponse showVisitors(String name) {

        int blogId = blogRepository.findByName(name).getId();

        List<Visitor> visitorList = visitorRepository.findByBlog_id(blogId);
        String blogName = visitorList.get(0).getBlog().getName();

        List<VisitorInfo> visitorInfo = new ArrayList<VisitorInfo>();

        for(int i = 0; i < visitorList.size(); i++){
            visitorInfo.add(i, VisitorInfo.of(visitorList.get(i).getUser().getNick_name(), visitorList.get(i).getDate()));
        }

        return ShowVisitorsResponse
            .of("Results of ShowVisitorsList", blogName, visitorInfo);
    }

    @Transactional
    public WriteGuestBookResponse writeGuestBook(String name,
        WriteGuestBookRequest writeGuestBookRequest) {
        GuestBook writeGuestBook = this.guestBookRepository.save(
            GuestBook.of(
                blogRepository.findByName(name),
                writeGuestBookRequest.getComment(),
                (User) session.getAttribute("user")
            )
        );
        return WriteGuestBookResponse.of("", writeGuestBook);
    }
}
