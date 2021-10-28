package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogInCheckResponse {

    private Boolean isLogin;
    private String responseMessage;

    public static LogInCheckResponse of(Boolean isLogin, String responseMessage) {
        return new LogInCheckResponse(isLogin, responseMessage);
    }
}
