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
public class BlogCheckResponse {

    private Boolean existence;

    private String blogName;

    public static BlogCheckResponse of(Boolean existence, String blogName) {
        return new BlogCheckResponse(existence, blogName);
    }
}
