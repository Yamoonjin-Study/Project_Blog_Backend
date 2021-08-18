package com.woonjin.blog.application.dto.request;

import com.woonjin.blog.domain.entity.Board;
import com.woonjin.blog.domain.entity.Reply;
import com.woonjin.blog.domain.entity.User;
import lombok.Getter;

@Getter
public class ReplyRequest {

    private Board top_board;

    private Reply top_reply;

    private String content;

    private User writer;
}
