package com.woonjin.blog.web;

import com.woonjin.blog.application.dto.response.CancelMessageResponse;
import com.woonjin.blog.application.dto.response.ChatListResponse;
import com.woonjin.blog.application.dto.response.ChatRoomResponse;
import com.woonjin.blog.application.service.ChatService;
import com.woonjin.blog.domain.entity.ChatMessage;
import com.woonjin.blog.domain.entity.ChatRoom;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"5. Chat Service"})
public class ChatController {

    private final ChatService chatService;

    public ChatController(
        ChatService chatService
    ) {
        this.chatService = chatService;
    }

    @GetMapping("/chat-list")
    public List<ChatListResponse> showChatList() {
//      시간순으로 정렬되고, 메세지 읽음 여부가 표시됨
        return this.chatService.showChatList();
    }

    @GetMapping("chat-room/{id}")
    public ChatRoomResponse showChatRoom(@PathVariable int id) {
//      상대가 안읽으면 표시가 되어있고, 읽으면 표시가 사라지게(본인도 마찬가지)
        return this.chatService.showChatRoom(id);
    }

    @PostMapping("chat-send")
    public ChatMessage sendMessage(String message, ChatRoom chatRoom) {
//
        return this.chatService.sendMessage(message, chatRoom);
    }

    @PutMapping("chat-delete/{id}")
    public CancelMessageResponse cancelMessage(@PathVariable int id) {
//      전송시간으로 부터 1분이내에만 삭제 가능 => "삭제된 메세지입니다." 로 수정됨
        return this.chatService.cancelMessage(id);
    }
}
