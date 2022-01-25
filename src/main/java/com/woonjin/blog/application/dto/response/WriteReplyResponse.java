package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.Board;
import com.woonjin.blog.domain.entity.Reply;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WriteReplyResponse {
    private Reply reply;

    private String responseMessage;

    public static WriteReplyResponse of(Reply reply, String responseMessage){
        return new WriteReplyResponse(reply, responseMessage);
    }
}
