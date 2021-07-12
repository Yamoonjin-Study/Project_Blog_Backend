package com.woonjin.blog.web;

import com.woonjin.blog.application.dto.request.CreateBusinessCardRequest;
import com.woonjin.blog.application.dto.request.CreatePortfolioRequest;
import com.woonjin.blog.application.dto.request.CreateResumeRequest;
import com.woonjin.blog.application.dto.response.ProfileResponse;
import com.woonjin.blog.application.service.ProfileService;
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
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/create-resume")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse 이력서생성(@RequestBody CreateResumeRequest createResumeRequest) {
        return profileService.createResume(createResumeRequest);
    }

    @PostMapping("/create-portfolio")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse 포트폴리오생성(@RequestBody CreatePortfolioRequest createPortfolioRequest) {
        return profileService.createPortfolio(createPortfolioRequest);
    }

    @PostMapping("/create-businesscard")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse 명함생성(@RequestBody CreateBusinessCardRequest createBusinessCardRequest) {
        return profileService.createBusinessCard(createBusinessCardRequest);
    }

    @PutMapping("/update-resume")
    public ProfileResponse 이력서수정(@RequestBody CreateResumeRequest createResumeRequest) {
        return profileService.updateResume(createResumeRequest);
    }

    @PutMapping("/update-portfolio")
    public ProfileResponse 포트폴리오수정(@RequestBody CreatePortfolioRequest createPortfolioRequest) {
        return profileService.updatePortfolio(createPortfolioRequest);
    }

    @PutMapping("/update-businesscard")
    public ProfileResponse 명함수정(@RequestBody CreateBusinessCardRequest createBusinessCardRequest) {
        return profileService.updateBusinessCard(createBusinessCardRequest);
    }

    @DeleteMapping("/delete-resume")
    public ProfileResponse 이력서삭제() {
        return profileService.deleteResume();
    }

    @DeleteMapping("/delete-portfolio")
    public ProfileResponse 포트폴리오삭제() {
        return profileService.deletePortfolio();
    }

    @DeleteMapping("/delete-businesscard")
    public ProfileResponse 명함삭제() {
        return profileService.deleteBusinessCard();
    }

}