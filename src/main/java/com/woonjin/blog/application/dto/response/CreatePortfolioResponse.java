package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.Portfolio;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePortfolioResponse {

    private String responseMessage;

    private Portfolio portfolio;

    public static CreatePortfolioResponse of(String responseMessage,
        Portfolio portfolio) {
        return new CreatePortfolioResponse(responseMessage, portfolio);
    }
}
