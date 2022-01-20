package com.woonjin.blog.application.dto.request;

import java.sql.Timestamp;
import lombok.Getter;

@Getter
public class UpdateReplyRequest {

    private int topBoardId;

    private int topReplyId;

    private String content;

    private Timestamp postDate;
}
