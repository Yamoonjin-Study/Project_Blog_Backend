package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.Like;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeReplyResponse {

    private String responseMessage;

    private Like like;

    public static LikeReplyResponse of(String responseMessage, Like like){
        return new LikeReplyResponse(responseMessage, like);
    }
}
