package com.woonjin.blog.application.dto.request;

import javax.persistence.Lob;
import lombok.Getter;

@Getter
public class BoardRequest {

    private String title;

    @Lob
    private String content;

    private String category;
}
