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
public class ProfileResponse {

    private String responseMessage;

    private Object profile;

    public static ProfileResponse of(String responseMessage, Object profile) {
        return new ProfileResponse(responseMessage, profile);
    }
}
