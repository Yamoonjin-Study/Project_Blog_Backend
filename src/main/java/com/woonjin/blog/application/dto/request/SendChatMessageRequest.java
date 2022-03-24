package com.woonjin.blog.application.dto.request;

import com.woonjin.blog.domain.entity.ChatRoom;
import javax.persistence.Lob;
import lombok.Getter;

@Getter
public class SendChatMessageRequest {

    private String message;

    private int chatRoomId;
}
