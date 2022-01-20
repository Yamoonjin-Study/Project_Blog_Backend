package com.woonjin.blog.application.dto.request;

import com.woonjin.blog.domain.entity.Blog.Category;
import lombok.Getter;

@Getter
public class CreateBlogRequest {

    private String blogName;

    private String info;

    private String iconImage;

    private String logoImage;

    private int mainContent;

    private int menuDesign;

    private Category category;
}
