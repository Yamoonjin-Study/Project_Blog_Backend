package com.woonjin.blog.application.dto.request;

import com.woonjin.blog.domain.entity.Category;
import lombok.Getter;

@Getter
public class BoardRequest {

    private String title;

    private String content;

    private Category category;
}
