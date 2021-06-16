package com.woonjin.blog.application.service;

import com.woonjin.blog.application.dto.request.CreatePortfolioRequest;
import com.woonjin.blog.domain.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class PortfolioService {

    private final HttpSession session;
    private final PortfolioRepository portfolioRepository;

    public PortfolioService(PortfolioRepository portfolioRepository, HttpSession session) {
        this.portfolioRepository = portfolioRepository;
        this.session = session;
    }

    public String createPortfolio() {
//        portfolioRepository.save(createPortfolioRequest);
        return "";
    }
}
