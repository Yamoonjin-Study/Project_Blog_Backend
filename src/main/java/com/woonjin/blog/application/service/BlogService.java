package com.woonjin.blog.application.service;

import com.woonjin.blog.application.dto.request.CreateBlogRequest;
import com.woonjin.blog.application.dto.request.UpdateBlogRequest;
import com.woonjin.blog.application.dto.request.WriteGuestBookRequest;
import com.woonjin.blog.application.dto.response.ActivateBlogResponse;
import com.woonjin.blog.application.dto.response.BlogCheckResponse;
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BlogService {

    private final IdentityAppService identityAppService;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final VisitorRepository visitorRepository;
    private final GuestBookRepository guestBookRepository;
    private final static Logger Log = Logger.getGlobal();

    public BlogService(
        IdentityAppService identityAppService,
        BlogRepository blogRepository,
        UserRepository userRepository,
        VisitorRepository visitorRepository,
        GuestBookRepository guestBookRepository
    ) {
        this.identityAppService = identityAppService;
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.visitorRepository = visitorRepository;
        this.guestBookRepository = guestBookRepository;
    }

    @Transactional(readOnly = true)
    public BlogCheckResponse checkBlogUser() {
        User user = this.identityAppService.getAuthenticationUser();
        Blog blog = this.blogRepository.findByUser_Id(user.getId());
        if (blog == null) {
            return BlogCheckResponse.of(false, "null");
        } else {
            return BlogCheckResponse.of(true, blog.getBlogName());
        }
    }

    @Transactional(readOnly = true)
    public ShowBlogResponse showBlog(String name) {
        if (this.blogRepository.findByBlogName(name) != null) {
            Log.info("Look Up Success");
            return ShowBlogResponse.of("Look Up Success", this.blogRepository.findByBlogName(name));
        } else {
            Log.info("No Result");
            return ShowBlogResponse.of("No Result", this.blogRepository.findByBlogName(name));
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
        User user = this.identityAppService.getAuthenticationUser();

        Blog createBlog = this.blogRepository.save(
            Blog.of(
                createBlogRequest.getBlogName(),
                createBlogRequest.getInfo(),
                createBlogRequest.getIconImage(),
                Blog.Status.ACTIVE,
                createBlogRequest.getLogoImage(),
                createBlogRequest.getMainContent(),
                createBlogRequest.getMenuDesign(),
                createBlogRequest.getCategory(),
                user,
                null,
                null,
                null
            )
        );

        user.setBlog(createBlog);
        this.userRepository.save(user);

        Log.info("Create Blog Success");
        return CreateBlogResponse.of("Create Blog Success", createBlog);
    }

    @Transactional
    public String saveFile(MultipartFile icon, MultipartFile logo) throws IOException {
        if (icon == null || icon.isEmpty() || logo == null || logo.isEmpty()) {
            Log.info("Save File Fail");
            return "file is empty";
        } else {
            String uploadIconFilePath = "/home/yamoonjin/바탕화면/Project/Blog_Project/blog_frontend/public/resources/iconImages/";
            String uploadLogoFilePath = "/home/yamoonjin/바탕화면/Project/Blog_Project/blog_frontend/public/resources/logoImages/";

            String prefix1 = icon.getOriginalFilename().substring(icon.getOriginalFilename().lastIndexOf("."));
            String filename1 = UUID.randomUUID().toString().replaceAll("-", "") + prefix1;

//            File folder = new File(uploadFilePath);
//            if(!folder.isDirectory()){
//                folder.mkdirs();
//            }

            String pathname1 = uploadIconFilePath + filename1;
            File dest1 = new File(pathname1);
            icon.transferTo(dest1);

            String prefix2 = logo.getOriginalFilename().substring(logo.getOriginalFilename().lastIndexOf("."));
            String filename2 = UUID.randomUUID().toString().replaceAll("-", "") + prefix2;
            String pathname2 = uploadLogoFilePath + filename2;
            File dest2 = new File(pathname2);
            logo.transferTo(dest2);

            User user = this.identityAppService.getAuthenticationUser();
            Blog blog = this.blogRepository.findByUser_Id(user.getId());
            String iconImgSrc = "/resources/iconImages/";
            String logoImgSrc = "/resources/logoImages/";
            blog.setIconImage(iconImgSrc+filename1);
            blog.setLogoImage(logoImgSrc+filename2);
            System.out.println(blog.getIconImage() + "/"+blog.getLogoImage());

            this.blogRepository.save(blog);

            Log.info("Save File Success");
            return "files are saved";
        }
    }

    @Transactional
    public String saveIconFile(MultipartFile icon) throws IOException {
            String uploadIconFilePath = "/home/yamoonjin/바탕화면/Project/Blog_Project/blog_frontend/public/resources/iconImages/";

            String prefix = icon.getOriginalFilename().substring(icon.getOriginalFilename().lastIndexOf("."));
            String filename = UUID.randomUUID().toString().replaceAll("-", "") + prefix;

            String pathname = uploadIconFilePath + filename;
            File dest = new File(pathname);
            icon.transferTo(dest);

            User user = this.identityAppService.getAuthenticationUser();
            Blog blog = this.blogRepository.findByUser_Id(user.getId());
            String iconImgSrc = "/resources/iconImages/";
            blog.setIconImage(iconImgSrc+filename);

            this.blogRepository.save(blog);

            Log.info("Save File Success");
            return "files are saved";

    }

    @Transactional
    public String saveLogoFile(MultipartFile logo) throws IOException {
            String uploadLogoFilePath = "/home/yamoonjin/바탕화면/Project/Blog_Project/blog_frontend/public/resources/logoImages/";

            String prefix = logo.getOriginalFilename().substring(logo.getOriginalFilename().lastIndexOf("."));
            String filename = UUID.randomUUID().toString().replaceAll("-", "") + prefix;
            String pathname = uploadLogoFilePath + filename;
            File dest = new File(pathname);
            logo.transferTo(dest);

            User user = this.identityAppService.getAuthenticationUser();
            Blog blog = this.blogRepository.findByUser_Id(user.getId());
            String logoImgSrc = "/resources/logoImages/";
            blog.setLogoImage(logoImgSrc+filename);

            this.blogRepository.save(blog);

            Log.info("Save File Success");
            return "files are saved";
    }

    @Transactional
    public UpdateBlogResponse updateBlogInfo(UpdateBlogRequest updateBlogRequest) {
        User user = this.identityAppService.getAuthenticationUser();
        Blog updateBlog = this.blogRepository.findByUser_Id(user.getId());

        updateBlog.setBlogName(updateBlogRequest.getName());
        updateBlog.setInfo(updateBlogRequest.getInfo());
        updateBlog.setCategory(updateBlogRequest.getCategory());

        this.blogRepository.save(updateBlog);

        Log.info("Update Blog Success");
        return UpdateBlogResponse.of("Update Blog Success", updateBlogRequest);
    }

    @Transactional
    public UpdateBlogResponse updateBlogDesign(UpdateBlogRequest updateBlogRequest) {
        User user = this.identityAppService.getAuthenticationUser();
        Blog updateBlog = this.blogRepository.findByUser_Id(user.getId());

        if (updateBlogRequest.getIcon() != null && !updateBlogRequest.getIcon().isEmpty()
            && updateBlogRequest.getLogoImage() != null && !updateBlogRequest.getLogoImage()
            .isEmpty()) {
            updateBlog.setIconImage(updateBlogRequest.getIcon());
            updateBlog.setLogoImage(updateBlogRequest.getLogoImage());
        }

        updateBlog.setMainContent(updateBlogRequest.getMainContent());
        updateBlog.setMenuDesign(updateBlogRequest.getMenuDesign());

        this.blogRepository.save(updateBlog);

        Log.info("Update Blog Success");
        return UpdateBlogResponse.of("Update Blog Success", updateBlogRequest);
    }

    @Transactional
    public ActivateBlogResponse activateBlog() {
        User user = this.identityAppService.getAuthenticationUser();
        Blog activateBlog = this.blogRepository.findByUser_Id(user.getId());
        activateBlog.activate(activateBlog);
        this.blogRepository.save(activateBlog);

        Log.info("Activation Success");
        return ActivateBlogResponse.of("Activation Success", activateBlog);
    }

    @Transactional
    public InactivateBlogResponse inactivateBlog() {
        User user = this.identityAppService.getAuthenticationUser();
        Blog inactivateBlog = this.blogRepository.findByUser_Id(user.getId());

        inactivateBlog.inactivate(inactivateBlog);

        this.blogRepository.save(inactivateBlog);

        Log.info("Inactivation Success");
        return InactivateBlogResponse.of("Inactivation Success", inactivateBlog);
    }

    @Transactional
    public DeleteBlogResponse deleteBlog() {
        User user = this.identityAppService.getAuthenticationUser();
        Blog blog = this.blogRepository.findByUser_Id(user.getId());

        this.blogRepository.delete(blog);

        Log.info("Delete Blog Success");
        return DeleteBlogResponse.of("Delete Blog Success", blog);
    }

    @Transactional
    public void addVisitors(String name) {
        Visitor addVisitor = new Visitor();

        addVisitor.setBlog(this.blogRepository.findByBlogName(name));
        addVisitor.setUser(this.identityAppService.getAuthenticationUser());

        this.visitorRepository.save(addVisitor);
    }

    @Transactional(readOnly = true)
    public ShowVisitorsResponse showVisitors(String name) {

        Blog blog = this.blogRepository.findByBlogName(name);

        List<Visitor> visitorList = this.visitorRepository.findByBlog(blog);
        String blogName = blog.getBlogName();

        List<VisitorInfo> visitorInfo = new ArrayList<VisitorInfo>();

        for (int i = 0; i < visitorList.size(); i++) {
            visitorInfo.add(i, VisitorInfo
                .of(visitorList.get(i).getUser().getNickName(), visitorList.get(i).getDate()));
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
                this.blogRepository.findByBlogName(name),
                writeGuestBookRequest.getComment(),
                identityAppService.getAuthenticationUser()
            )
        );

        Log.info("Write GuestBook Success");
        return WriteGuestBookResponse.of("Write GuestBook Success", writeGuestBook);
    }

    @Transactional
    public WriteGuestBookResponse deleteGuestBook(int id) {
        GuestBook guestBook = this.guestBookRepository.findById(id);
        this.guestBookRepository.delete(guestBook);

        Log.info("Delete GuestBook Success");
        return WriteGuestBookResponse.of("Delete GuestBook Success", null);
    }

    @Transactional(readOnly = true)
    public GuestBookListResponse showGuestBook(String name) {
        Blog blog = this.blogRepository.findByBlogName(name);
        List<GuestBook> guestBook = this.guestBookRepository.findByBlogOrderByDateDesc(blog);
        String blogname = blog.getBlogName();

        List<GuestBookList> guestBookList = new ArrayList<GuestBookList>();

        for (int i = 0; i < guestBook.size(); i++) {
            guestBookList.add(i, GuestBookList
                .of(guestBook.get(i).getId(), guestBook.get(i).getUser().getId(), guestBook.get(i).getComment(), guestBook.get(i).getDate(),
                    guestBook.get(i).getUser().getNickName(), guestBook.get(i).getUser().getBlog().getIconImage()));
        }

        Log.info("Results of ShowGuestBookList");
        return GuestBookListResponse.of("Results of ShowGuestBookList", blogname, guestBookList);
    }
}
