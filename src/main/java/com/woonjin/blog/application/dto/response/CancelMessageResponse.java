package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.ChatMessage;
import com.woonjin.blog.domain.entity.ChatRoom;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CancelMessageResponse {

    private String responseMessage;

    private ChatMessage chatMessage;

    public static CancelMessageResponse of(String responseMessage, ChatMessage chatMessage) {
        return new CancelMessageResponse(responseMessage, chatMessage);
    }
}
