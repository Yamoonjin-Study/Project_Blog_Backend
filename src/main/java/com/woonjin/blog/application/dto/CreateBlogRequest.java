package com.woonjin.blog.application.dto;

import com.woonjin.blog.domain.entity.Blog;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateBlogRequest {
    private String blogname;

    private String nickname;

    private String info;

    private String icon;
}
