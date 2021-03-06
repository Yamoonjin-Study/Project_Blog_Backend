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
public class LogInResponse {

    private String responseMessage;

    private String token;

    private User user;

    public static LogInResponse of(String responseMessage, String token, User user) {
        return new LogInResponse(responseMessage, token, user);
    }
}
