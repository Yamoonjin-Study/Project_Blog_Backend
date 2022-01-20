package com.woonjin.blog.application.dto.request;

import lombok.Getter;

@Getter
public class ReplyRequest {

    private int topBoardId;

    private int topReplyId;

    private String content;
}
