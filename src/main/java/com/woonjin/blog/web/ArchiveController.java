package com.woonjin.blog.web;

import com.woonjin.blog.application.dto.request.CreateArchiveRequest;
import com.woonjin.blog.application.dto.response.ArchiveResponse;
import com.woonjin.blog.application.dto.response.CreateArchiveResponse;
import com.woonjin.blog.application.service.ArchiveService;
import io.swagger.annotations.Api;
import java.io.IOException;
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
@Api(tags = {"3. Profile Service"})
public class ArchiveController {

    private final ArchiveService archiveService;

    public ArchiveController(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @GetMapping("/show-archives/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ArchiveResponse showArchive(@PathVariable String name) {
        return this.archiveService.showArchive(name);
    }

    @PostMapping("/create-archive")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateArchiveResponse CreateArchive(@RequestBody CreateArchiveRequest createArchiveRequest) {
        return this.archiveService.createArchive(createArchiveRequest);
    }

    @PostMapping("/upload-file/{id}")
    public String UploadFile(@PathVariable int id,
        @RequestPart(value = "pdfFile") MultipartFile pdfFile
    ) throws IOException {
        return this.archiveService.uploadFile(id, pdfFile);
    }

    @PutMapping("/update-archive{id}")
    public CreateArchiveResponse UpdateArchive(@RequestBody CreateArchiveRequest createArchiveRequest, @PathVariable int id) {
        return archiveService.updateArchive(createArchiveRequest, id);
    }

    @DeleteMapping("/delete-archive/{id}")
    public CreateArchiveResponse DeleteArchive(@PathVariable int id) {
        return archiveService.deleteArchive(id);
    }
}