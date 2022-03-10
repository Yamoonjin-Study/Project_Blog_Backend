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
public class ChatListResponse {

    private ChatRoom chatRoom;

    private ChatMessage chatMessage;

    private Boolean chatReadingCheck;

    public static ChatListResponse of(ChatRoom chatRoom, ChatMessage chatMessage, Boolean chatReadingCheck) {
        return new ChatListResponse(chatRoom, chatMessage, chatReadingCheck);
    }
}
