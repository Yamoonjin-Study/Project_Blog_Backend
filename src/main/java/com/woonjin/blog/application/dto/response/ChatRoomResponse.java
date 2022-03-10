package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.ChatMessage;
import com.woonjin.blog.domain.entity.ChatRoom;
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
public class ChatRoomResponse {

    private String responseMessage;

    private ChatRoom chatRoom;

    private List<ChatMessage> chatMessage;

    public static ChatRoomResponse of(
        String responseMessage,
        ChatRoom chatRoom,
        List<ChatMessage> chatMessage
    ) {
        return new ChatRoomResponse(responseMessage, chatRoom, chatMessage);
    }
}
