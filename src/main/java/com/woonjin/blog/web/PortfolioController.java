package com.woonjin.blog.web;

import com.woonjin.blog.application.dto.request.CreatePortfolioRequest;
import com.woonjin.blog.application.service.PortfolioService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"3. Portfolio Service"})
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping("/create-portfolio")
    public String 포트폴리오생성() {
//        return portfolioService.createPortfolio(createPortfolioRequest);
        return "";
    }
}