package com.woonjin.blog.application.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBlogRequest {

    private String blogname;

    private String nickname;

    private String info;

    private String icon;
}
