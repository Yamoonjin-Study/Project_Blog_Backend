package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.Blog;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ActivateBlogResponse {

    private String responseMessage;

    private Blog blog;

    public static ActivateBlogResponse of(String responseMessage, Blog blog) {
        return new ActivateBlogResponse(responseMessage, blog);
    }
}
