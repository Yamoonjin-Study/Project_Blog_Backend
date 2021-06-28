package com.woonjin.blog.application.service;

import com.woonjin.blog.application.dto.request.CreateBusinessCardRequest;
import com.woonjin.blog.application.dto.request.CreatePortfolioRequest;
import com.woonjin.blog.application.dto.request.CreateResumeRequest;
import com.woonjin.blog.application.dto.response.ProfileResponse;
import com.woonjin.blog.domain.entity.BusinessCard;
import com.woonjin.blog.domain.entity.Portfolio;
import com.woonjin.blog.domain.entity.Portfolio.Type;
import com.woonjin.blog.domain.entity.Resume;
import com.woonjin.blog.domain.entity.User;
import com.woonjin.blog.domain.repository.BusinessCardRepository;
import com.woonjin.blog.domain.repository.PortfolioRepository;
import com.woonjin.blog.domain.repository.ResumeRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ProfileService {

    private final HttpSession session;
    private final PortfolioRepository portfolioRepository;
    private final ResumeRepository resumeRepository;
    private final BusinessCardRepository businessCardRepository;

    public ProfileService(PortfolioRepository portfolioRepository,
        ResumeRepository resumeRepository, BusinessCardRepository businessCardRepository,
        HttpSession session) {
        this.portfolioRepository = portfolioRepository;
        this.resumeRepository = resumeRepository;
        this.businessCardRepository = businessCardRepository;
        this.session = session;
    }

    public ProfileResponse createPortfolio(CreatePortfolioRequest createPortfolioRequest) {
        User user = (User) session.getAttribute("user");

        Portfolio createPortfolio = this.portfolioRepository.save(
            Portfolio.of(
                Type.PORTFOLIO,
                createPortfolioRequest.getContent(),
                createPortfolioRequest.getTitle(),
                user
            )
        );

        return ProfileResponse.of("Create Portfolio Success", createPortfolio);
    }

    public ProfileResponse createResume(CreateResumeRequest createResumeRequest) {
        User user = (User) session.getAttribute("user");

        Resume resume = this.resumeRepository.save(
            Resume.of(
                Resume.Type.RESUME,
                createResumeRequest.getContent(),
                createResumeRequest.getTitle(),
                user
            )
        );

        return ProfileResponse.of("Create Resume Success", resume);
    }

    public ProfileResponse createBusinessCard(
        CreateBusinessCardRequest createBusinessCardRequest) {
        User user = (User) session.getAttribute("user");

        BusinessCard businessCard = this.businessCardRepository.save(
            BusinessCard.of(
                BusinessCard.Type.BUSINESSCARD,
                createBusinessCardRequest.getContent(),
                createBusinessCardRequest.getTitle(),
                user
            )
        );

        return ProfileResponse.of("Create BusinessCard Success", businessCard);
    }

    public ProfileResponse updatePortfolio(CreatePortfolioRequest updatePortfolioRequest) {
        User user = (User) session.getAttribute("user");
        Portfolio updatePortfolio = portfolioRepository.findByUser(user);

        updatePortfolio.setContent(updatePortfolioRequest.getContent());
        updatePortfolio.setTitle(updatePortfolioRequest.getTitle());

        portfolioRepository.save(updatePortfolio);

        return ProfileResponse.of("Update Portfolio Success", updatePortfolio);
    }

    public ProfileResponse updateResume(CreateResumeRequest updateResumeRequest) {
        User user = (User) session.getAttribute("user");
        Resume updateResume = resumeRepository.findByUser(user);

        updateResume.setContent(updateResumeRequest.getContent());
        updateResume.setTitle(updateResumeRequest.getTitle());

        resumeRepository.save(updateResume);

        return ProfileResponse.of("Update Resume Success", updateResume);
    }

    public ProfileResponse updateBusinessCard(
        CreateBusinessCardRequest updateBusinessCardRequest) {
        User user = (User) session.getAttribute("user");
        BusinessCard updateBusinessCard = businessCardRepository.findByUser(user);

        updateBusinessCard.setContent(updateBusinessCardRequest.getContent());
        updateBusinessCard.setTitle(updateBusinessCardRequest.getTitle());

        businessCardRepository.save(updateBusinessCard);

        return ProfileResponse.of("Update BusinessCard Success", updateBusinessCard);
    }

    public ProfileResponse deleteBusinessCard() {
        User user = (User) session.getAttribute("user");
        BusinessCard deleteBusinessCard = businessCardRepository.findByUser(user);

        businessCardRepository.delete(deleteBusinessCard);

        return ProfileResponse.of("Delete BusinessCard Success", deleteBusinessCard);
    }

    public ProfileResponse deletePortfolio() {
        User user = (User) session.getAttribute("user");
        Portfolio deletePortfolio = portfolioRepository.findByUser(user);

        portfolioRepository.delete(deletePortfolio);

        return ProfileResponse.of("Delete Portfolio Success", deletePortfolio);
    }

    public ProfileResponse deleteResume() {
        User user = (User) session.getAttribute("user");
        Resume deleteResume = resumeRepository.findByUser(user);

        resumeRepository.delete(deleteResume);

        return ProfileResponse.of("Delete Resume Success", deleteResume);
    }
}
