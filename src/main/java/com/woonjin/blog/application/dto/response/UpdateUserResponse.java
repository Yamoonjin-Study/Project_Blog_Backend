package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.application.dto.request.UpdateUserRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateUserResponse {

    private String responseMessage;

    private UpdateUserRequest updateUserRequest;

    public static UpdateUserResponse of(String responseMessage, UpdateUserRequest updateUserRequest) {
        return new UpdateUserResponse(responseMessage, updateUserRequest);
    }
}
