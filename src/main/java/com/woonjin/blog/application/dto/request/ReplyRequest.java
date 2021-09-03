package com.woonjin.blog.application.dto.request;

import lombok.Getter;

@Getter
public class ReplyRequest {

    private int top_board_id;

    private int top_reply_id;

    private String content;
}
