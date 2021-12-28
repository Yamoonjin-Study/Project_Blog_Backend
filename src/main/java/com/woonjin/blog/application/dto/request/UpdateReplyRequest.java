package com.woonjin.blog.application.dto.request;

import java.sql.Timestamp;
import lombok.Getter;

@Getter
public class UpdateReplyRequest {

    private int top_board_id;

    private int top_reply_id;

    private String content;

    private Timestamp post_date;
}
