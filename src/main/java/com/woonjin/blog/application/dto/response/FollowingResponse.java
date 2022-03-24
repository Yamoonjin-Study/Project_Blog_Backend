package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.Follow;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowingResponse {

    private String responseMessage;

    private Follow following;

    public static FollowingResponse of(String responseMessage, Follow following) {
        return new FollowingResponse(responseMessage, following);
    }
}
