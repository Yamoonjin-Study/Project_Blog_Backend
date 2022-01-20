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
public class LikeBoardResponse {

    private String responseMessage;

    private Like like;

    public static LikeBoardResponse of(String responseMessage, Like like){
        return new LikeBoardResponse(responseMessage, like);
    }
}
