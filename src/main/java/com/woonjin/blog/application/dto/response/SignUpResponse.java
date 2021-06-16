package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.application.dto.request.SignUpRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpResponse {

    private String responseMessage;

    private SignUpRequest signUpRequest;

    public static SignUpResponse of(String responseMessage, SignUpRequest signUpRequest) {
        return new SignUpResponse(responseMessage, signUpRequest);
    }
}
