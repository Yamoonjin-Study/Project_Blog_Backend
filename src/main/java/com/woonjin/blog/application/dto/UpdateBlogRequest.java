package com.woonjin.blog.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateBlogRequest {

    private String blogname;

    private String nickname;

    private String info;

    private String icon;
}
