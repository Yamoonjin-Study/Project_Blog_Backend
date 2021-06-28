package com.woonjin.blog.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateResumeRequest {

    private String content;

    private String title;
}
