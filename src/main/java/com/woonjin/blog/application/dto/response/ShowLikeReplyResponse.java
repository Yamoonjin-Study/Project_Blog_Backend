package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.Like;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShowLikeReplyResponse {

    private String responseMessage;

    private List<Like> like;

    public static ShowLikeReplyResponse of(String responseMessage, List<Like> like){
        return new ShowLikeReplyResponse(responseMessage, like);
    }
}
