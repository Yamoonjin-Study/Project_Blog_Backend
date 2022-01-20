package com.woonjin.blog.application.dto.request;

import com.woonjin.blog.domain.entity.Blog.Category;
import lombok.Getter;

@Getter
public class UpdateBlogRequest {

    private String name;

    private String info;

    private String icon;

    private String logoImage;

    private int mainContent;

    private int menuDesign;

    private Category category;
}
