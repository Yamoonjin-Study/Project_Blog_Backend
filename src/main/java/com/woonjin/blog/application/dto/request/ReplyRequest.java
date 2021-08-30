package com.woonjin.blog.application.dto.request;

import lombok.Getter;

@Getter
public class ReplyRequest {

    private int top_board;

    private int top_reply;

    private String content;
}
