package com.woonjin.blog.application.dto.request;

import com.woonjin.blog.domain.entity.Archive.Type;
import lombok.Getter;

@Getter
public class CreateArchiveRequest {
    private String content;

    private String title;

    private Type type;
}
