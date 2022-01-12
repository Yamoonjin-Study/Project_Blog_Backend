package com.woonjin.blog.application.service;

import com.woonjin.blog.application.dto.request.CreateArchiveRequest;
import com.woonjin.blog.application.dto.response.ArchiveResponse;
import com.woonjin.blog.domain.entity.Archive;
import com.woonjin.blog.domain.entity.Archive.Type;
import com.woonjin.blog.domain.entity.Blog;
import com.woonjin.blog.domain.entity.User;
import com.woonjin.blog.domain.repository.ArchiveRepository;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArchiveService {

    private final IdentityAppService identityAppService;
    private final ArchiveRepository archiveRepository;
    private final static Logger Log = Logger.getGlobal();

    public ArchiveService(
        IdentityAppService identityAppService,
        ArchiveRepository archiveRepository
    ) {
        this.archiveRepository = archiveRepository;
        this.identityAppService = identityAppService;
    }

    public ArchiveResponse createArchive(CreateArchiveRequest createArchiveRequest) {
        User user = this.identityAppService.getAuthenticationUser();

        Archive createArchive = this.archiveRepository.save(
            Archive.of(
                createArchiveRequest.getType(),
                createArchiveRequest.getContent(),
                createArchiveRequest.getTitle(),
                null,
                user.getBlog()
            )
        );

        Log.info("Create Archive Success");
        return ArchiveResponse.of("Create Archive Success", createArchive);
    }

    @Transactional
    public String uploadFile(int id, MultipartFile pdfFile) throws IOException {

        if(pdfFile == null || pdfFile.isEmpty()){
            Log.info("Upload File Fail");
            return "file is empty";
        }else{
            String uploadIconFilePath = "/home/yamoonjin/바탕화면/Project/Blog_Project/blog_frontend/public/resources/archiveFiles/pdfFiles/";
            String fileSrc = "/resources/archiveFiles/pdfFiles/";
            Archive archive = this.archiveRepository.findById(id);
            String prefix = pdfFile.getOriginalFilename().substring(pdfFile.getOriginalFilename().lastIndexOf("."));
            String filename = UUID.randomUUID().toString().replaceAll("-", "") + prefix;
            archive.setFile_path(fileSrc+filename);
            String pathname = uploadIconFilePath + filename;
            File dest = new File(pathname);
            pdfFile.transferTo(dest);

            this.archiveRepository.save(archive);

            Log.info("Upload File Success");
            return "files are uploaded";
        }
    }

    public ArchiveResponse updateArchive(CreateArchiveRequest updateArchiveRequest) {
        User user = this.identityAppService.getAuthenticationUser();
        Archive updateArchive = this.archiveRepository.findByBlog(user.getBlog());

        updateArchive.setContent(updateArchiveRequest.getContent());
        updateArchive.setTitle(updateArchiveRequest.getTitle());

        this.archiveRepository.save(updateArchive);

        Log.info("Update Resume Success");
        return ArchiveResponse.of("Update Resume Success", updateArchive);
    }

    public ArchiveResponse deleteArchive() {
        User user = this.identityAppService.getAuthenticationUser();
        Archive deleteArchive = this.archiveRepository.findByBlog(user.getBlog());

        this.archiveRepository.delete(deleteArchive);

        Log.info("Delete Archive Success");
        return ArchiveResponse.of("Delete Archive Success", deleteArchive);
    }
}
