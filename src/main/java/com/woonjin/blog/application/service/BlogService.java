package com.woonjin.blog.application.service;

import com.woonjin.blog.application.dto.request.CreateBlogRequest;
import com.woonjin.blog.application.dto.request.UpdateBlogRequest;
import com.woonjin.blog.application.dto.request.WriteGuestBookRequest;
import com.woonjin.blog.application.dto.response.ActivateBlogResponse;
import com.woonjin.blog.application.dto.response.CreateBlogResponse;
import com.woonjin.blog.application.dto.response.DeleteBlogResponse;
import com.woonjin.blog.application.dto.response.GuestBookList;
import com.woonjin.blog.application.dto.response.GuestBookListResponse;
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
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogService {
    private final IdentityAppService identityAppService;
    private final BlogRepository blogRepository;
    private final VisitorRepository visitorRepository;
    private final GuestBookRepository guestBookRepository;
    private final UserRepository userRepository;
    private final static Logger Log = Logger.getGlobal();

    public BlogService(
        IdentityAppService identityAppService,
        BlogRepository blogRepository,
        VisitorRepository visitorRepository,
        GuestBookRepository guestBookRepository,
        UserRepository userRepository
    ) {
        this.identityAppService = identityAppService;
        this.blogRepository = blogRepository;
        this.visitorRepository = visitorRepository;
        this.guestBookRepository = guestBookRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public ShowBlogResponse showBlog(String name) {
        if (this.blogRepository.findByName(name) != null) {
            Log.info("Look Up Success");
            return ShowBlogResponse.of("Look Up Success", this.blogRepository.findByName(name));
        } else {
            Log.info("No Result");
            return ShowBlogResponse.of("No Result", this.blogRepository.findByName(name));
        }
    }

    @Transactional(readOnly = true)
    public SearchBlogResponse searchBlog(String name) {

        List<String> list = this.blogRepository.searchBlog(name);

        Log.info("Results of searching");
        return SearchBlogResponse.of("Results of searching", list);
    }

    @Transactional
    public CreateBlogResponse createBlog(CreateBlogRequest createBlogRequest) {
        User user = identityAppService.getAuthenticationUser();

        Blog createBlog = this.blogRepository.save(
            Blog.of(
                createBlogRequest.getName(),
                createBlogRequest.getInfo(),
                createBlogRequest.getIcon(),
                Blog.Status.ACTIVE,
                createBlogRequest.getLogo_image(),
                createBlogRequest.getDesign_form(),
                createBlogRequest.getCategory(),
                user
            )
        );
        Log.info("Create Blog Success");
        return CreateBlogResponse.of("Create Blog Success", createBlog);
    }

    @Transactional
    public UpdateBlogResponse updateBlog(UpdateBlogRequest updateBlogRequest) {
        User user = identityAppService.getAuthenticationUser();
        Blog updateBlog = this.blogRepository.findByUser_Id(user.getId());

        updateBlog.setName(updateBlogRequest.getName());
        updateBlog.setIcon(updateBlogRequest.getIcon());
        updateBlog.setInfo(updateBlogRequest.getInfo());
        updateBlog.setLogo_image(updateBlogRequest.getLogo_image());
        updateBlog.setDesign_form(updateBlogRequest.getDesign_form());
        updateBlog.setCategory(updateBlogRequest.getCategory());

        this.blogRepository.save(updateBlog);

        Log.info("Update Blog Success");
        return UpdateBlogResponse.of("Update Blog Success", updateBlogRequest);
    }

    @Transactional
    public ActivateBlogResponse activateBlog() {
        User user = identityAppService.getAuthenticationUser();
        Blog activateBlog = this.blogRepository.findByUser_Id(user.getId());
        activateBlog.activate(activateBlog);
        this.blogRepository.save(activateBlog);

        Log.info("Activation Success");
        return ActivateBlogResponse.of("Activation Success", activateBlog);
    }

    @Transactional
    public InactivateBlogResponse inactivateBlog() {
        User user = identityAppService.getAuthenticationUser();
        Blog inactivateBlog = this.blogRepository.findByUser_Id(user.getId());

        inactivateBlog.inactivate(inactivateBlog);

        this.blogRepository.save(inactivateBlog);

        Log.info("Inactivation Success");
        return InactivateBlogResponse.of("Inactivation Success", inactivateBlog);
    }

    @Transactional
    public DeleteBlogResponse deleteBlog() {
        User user = identityAppService.getAuthenticationUser();
        Blog blog = this.blogRepository.findByUser_Id(user.getId());

        this.blogRepository.delete(blog);

        Log.info("Delete Blog Success");
        return DeleteBlogResponse.of("Delete Blog Success", blog);
    }

    @Transactional
    public void addVisitors(String name) {
        Visitor addVisitor = new Visitor();

        addVisitor.setBlog(this.blogRepository.findByName(name));
        addVisitor.setUser(identityAppService.getAuthenticationUser());

        this.visitorRepository.save(addVisitor);
    }

    @Transactional(readOnly = true)
    public ShowVisitorsResponse showVisitors(String name) {

        Blog blog = this.blogRepository.findByName(name);

        List<Visitor> visitorList = this.visitorRepository.findByBlog(blog);
        String blogName = blog.getName();

        List<VisitorInfo> visitorInfo = new ArrayList<VisitorInfo>();

        for (int i = 0; i < visitorList.size(); i++) {
            visitorInfo.add(i, VisitorInfo
                .of(visitorList.get(i).getUser().getNick_name(), visitorList.get(i).getDate()));
        }

        Log.info("Results of ShowVisitorsList");
        return ShowVisitorsResponse
            .of("Results of ShowVisitorsList", blogName, visitorInfo);
    }

    @Transactional
    public WriteGuestBookResponse writeGuestBook(String name,
        WriteGuestBookRequest writeGuestBookRequest) {
        GuestBook writeGuestBook = this.guestBookRepository.save(
            GuestBook.of(
                this.blogRepository.findByName(name),
                writeGuestBookRequest.getComment(),
                identityAppService.getAuthenticationUser()
            )
        );

        Log.info("Write GuestBook Success");
        return WriteGuestBookResponse.of("Write GuestBook Success", writeGuestBook);
    }

    @Transactional(readOnly = true)
    public GuestBookListResponse showGuestBook(String name) {
        Blog blog = this.blogRepository.findByName(name);
        List<GuestBook> guestBook = this.guestBookRepository.findByBlog(blog);
        String blogname = blog.getName();

        List<GuestBookList> guestBookList = new ArrayList<GuestBookList>();

        for (int i = 0; i < guestBook.size(); i++) {
            guestBookList.add(i, GuestBookList
                .of(guestBook.get(i).getComment(), guestBook.get(i).getDate(),
                    guestBook.get(i).getUser().getNick_name()));
        }

        Log.info("Results of ShowGuestBookList");
        return GuestBookListResponse.of("Results of ShowGuestBookList", blogname, guestBookList);
    }
}
