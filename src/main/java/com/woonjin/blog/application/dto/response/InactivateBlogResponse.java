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
public class InactivateBlogResponse {

    private String responseMessage;

    private Blog blog;

    public static InactivateBlogResponse of(String responseMessage, Blog blog) {
        return new InactivateBlogResponse(responseMessage, blog);
    }
}
