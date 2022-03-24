package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.application.dto.request.UpdateBlogRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateBlogResponse {

    private String responseMessage;

    private UpdateBlogRequest updateBlogRequest;

    public static UpdateBlogResponse of(String responseMessage, UpdateBlogRequest updateBlogRequest) {
        return new UpdateBlogResponse(responseMessage, updateBlogRequest);
    }
}
