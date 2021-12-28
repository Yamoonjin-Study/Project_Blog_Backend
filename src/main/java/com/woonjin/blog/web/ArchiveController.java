package com.woonjin.blog.web;

import com.woonjin.blog.application.dto.request.CreateArchiveRequest;
import com.woonjin.blog.application.dto.response.ArchiveResponse;
import com.woonjin.blog.application.service.ArchiveService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"3. Profile Service"})
public class ArchiveController {

    private final ArchiveService profileService;

    public ArchiveController(ArchiveService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/create-archive")
    @ResponseStatus(HttpStatus.CREATED)
    public ArchiveResponse CreateArchive(@RequestBody CreateArchiveRequest createArchiveRequest) {
        return profileService.createArchive(createArchiveRequest);
    }

    @PutMapping("/update-archive")
    public ArchiveResponse UpdateArchive(@RequestBody CreateArchiveRequest createArchiveRequest) {
        return profileService.updateArchive(createArchiveRequest);
    }

    @DeleteMapping("/delete-archive")
    public ArchiveResponse DeleteArchive() {
        return profileService.deleteArchive();
    }
}