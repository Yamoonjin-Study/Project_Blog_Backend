package com.woonjin.blog.application.service;

import com.woonjin.blog.application.dto.request.CreateArchiveRequest;
import com.woonjin.blog.application.dto.response.ArchiveResponse;
import com.woonjin.blog.domain.entity.Archive;
import com.woonjin.blog.domain.entity.User;
import com.woonjin.blog.domain.repository.ArchiveRepository;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

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
                createArchiveRequest.getFile(),
                user
            )
        );

        Log.info("Create Portfolio Success");
        return ArchiveResponse.of("Create Portfolio Success", createArchive);
    }

    public ArchiveResponse updateArchive(CreateArchiveRequest updateArchiveRequest) {
        User user = this.identityAppService.getAuthenticationUser();
        Archive updateArchive = this.archiveRepository.findByUser(user);

        updateArchive.setContent(updateArchiveRequest.getContent());
        updateArchive.setTitle(updateArchiveRequest.getTitle());
        updateArchive.setFile(updateArchiveRequest.getFile());

        this.archiveRepository.save(updateArchive);

        Log.info("Update Resume Success");
        return ArchiveResponse.of("Update Resume Success", updateArchive);
    }

    public ArchiveResponse deleteArchive() {
        User user = this.identityAppService.getAuthenticationUser();
        Archive deleteArchive = this.archiveRepository.findByUser(user);

        this.archiveRepository.delete(deleteArchive);

        Log.info("Delete Archive Success");
        return ArchiveResponse.of("Delete Archive Success", deleteArchive);
    }
}
