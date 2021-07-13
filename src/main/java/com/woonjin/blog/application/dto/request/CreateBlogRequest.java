package com.woonjin.blog.application.dto.request;


import com.woonjin.blog.domain.entity.Blog.Category;
import lombok.Getter;

@Getter
public class CreateBlogRequest {

    private String name;

    private String info;

    private String icon;

    private String logo_image;

    private int design_form;

    private Category category;
}
