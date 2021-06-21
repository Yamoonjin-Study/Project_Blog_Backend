package com.woonjin.blog.application.dto.request;

import com.woonjin.blog.domain.entity.Blog.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBlogRequest {

    private String name;

    private String nick_name;

    private String info;

    private String icon;

    private String logo_image;

    private int design_form;

    private Category category;
}
