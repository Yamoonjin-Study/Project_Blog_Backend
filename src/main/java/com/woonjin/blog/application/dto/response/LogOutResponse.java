package com.woonjin.blog.application.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogOutResponse {

    private String responseMessage;

    public static LogOutResponse of(String responseMessage) {
        return new LogOutResponse(responseMessage);
    }
}
