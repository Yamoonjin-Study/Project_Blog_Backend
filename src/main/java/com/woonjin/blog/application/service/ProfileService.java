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
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ProfileService {

    private final HttpSession session;
    private final PortfolioRepository portfolioRepository;
    private final ResumeRepository resumeRepository;
    private final BusinessCardRepository businessCardRepository;
    private final static Logger Log = Logger.getGlobal();

    public ProfileService(
        PortfolioRepository portfolioRepository,
        ResumeRepository resumeRepository,
        BusinessCardRepository businessCardRepository,
        HttpSession session
    ) {
        this.portfolioRepository = portfolioRepository;
        this.resumeRepository = resumeRepository;
        this.businessCardRepository = businessCardRepository;
        this.session = session;
    }

    public ProfileResponse createPortfolio(CreatePortfolioRequest createPortfolioRequest) {
        User user = (User) this.session.getAttribute("user");

        Portfolio createPortfolio = this.portfolioRepository.save(
            Portfolio.of(
                Type.PORTFOLIO,
                createPortfolioRequest.getContent(),
                createPortfolioRequest.getTitle(),
                user
            )
        );

        Log.info("Create Portfolio Success");
        return ProfileResponse.of("Create Portfolio Success", createPortfolio);
    }

    public ProfileResponse createResume(CreateResumeRequest createResumeRequest) {
        User user = (User) this.session.getAttribute("user");

        Resume resume = this.resumeRepository.save(
            Resume.of(
                Resume.Type.RESUME,
                createResumeRequest.getContent(),
                createResumeRequest.getTitle(),
                user
            )
        );

        Log.info("Create Resume Success");
        return ProfileResponse.of("Create Resume Success", resume);
    }

    public ProfileResponse createBusinessCard(
        CreateBusinessCardRequest createBusinessCardRequest) {
        User user = (User) this.session.getAttribute("user");

        BusinessCard businessCard = this.businessCardRepository.save(
            BusinessCard.of(
                BusinessCard.Type.BUSINESSCARD,
                createBusinessCardRequest.getContent(),
                createBusinessCardRequest.getTitle(),
                user
            )
        );

        Log.info("Create BusinessCard Success");
        return ProfileResponse.of("Create BusinessCard Success", businessCard);
    }

    public ProfileResponse updatePortfolio(CreatePortfolioRequest updatePortfolioRequest) {
        User user = (User) this.session.getAttribute("user");
        Portfolio updatePortfolio = this.portfolioRepository.findByUser(user);

        updatePortfolio.setContent(updatePortfolioRequest.getContent());
        updatePortfolio.setTitle(updatePortfolioRequest.getTitle());

        this.portfolioRepository.save(updatePortfolio);

        Log.info("Update Portfolio Success");
        return ProfileResponse.of("Update Portfolio Success", updatePortfolio);
    }

    public ProfileResponse updateResume(CreateResumeRequest updateResumeRequest) {
        User user = (User) this.session.getAttribute("user");
        Resume updateResume = this.resumeRepository.findByUser(user);

        updateResume.setContent(updateResumeRequest.getContent());
        updateResume.setTitle(updateResumeRequest.getTitle());

        this.resumeRepository.save(updateResume);

        Log.info("Update Resume Success");
        return ProfileResponse.of("Update Resume Success", updateResume);
    }

    public ProfileResponse updateBusinessCard(
        CreateBusinessCardRequest updateBusinessCardRequest) {
        User user = (User) this.session.getAttribute("user");
        BusinessCard updateBusinessCard = this.businessCardRepository.findByUser(user);

        updateBusinessCard.setContent(updateBusinessCardRequest.getContent());
        updateBusinessCard.setTitle(updateBusinessCardRequest.getTitle());

        this.businessCardRepository.save(updateBusinessCard);

        Log.info("Update BusinessCard Success");
        return ProfileResponse.of("Update BusinessCard Success", updateBusinessCard);
    }

    public ProfileResponse deleteBusinessCard() {
        User user = (User) this.session.getAttribute("user");
        BusinessCard deleteBusinessCard = this.businessCardRepository.findByUser(user);

        this.businessCardRepository.delete(deleteBusinessCard);

        Log.info("Delete BusinessCard Success");
        return ProfileResponse.of("Delete BusinessCard Success", deleteBusinessCard);
    }

    public ProfileResponse deletePortfolio() {
        User user = (User) this.session.getAttribute("user");
        Portfolio deletePortfolio = this.portfolioRepository.findByUser(user);

        this.portfolioRepository.delete(deletePortfolio);

        Log.info("Delete Portfolio Success");
        return ProfileResponse.of("Delete Portfolio Success", deletePortfolio);
    }

    public ProfileResponse deleteResume() {
        User user = (User) this.session.getAttribute("user");
        Resume deleteResume = this.resumeRepository.findByUser(user);

        this.resumeRepository.delete(deleteResume);

        Log.info("Delete Resume Success");
        return ProfileResponse.of("Delete Resume Success", deleteResume);
    }
}
