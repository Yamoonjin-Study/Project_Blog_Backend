package com.woonjin.blog.web;

import com.woonjin.blog.application.service.ChatService;
import com.woonjin.blog.domain.entity.Chat;
import io.swagger.annotations.Api;
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
    ){
        this.chatService = chatService;
    }

    @GetMapping("/chat-list")
    public void 채팅방리스트(){
//      읽지 않은 메시시지가 있는 채팅방이 다르게 구분되고, 상단에 표시 되게끔

    }

    @GetMapping("chat/{id}")
    public void 채팅내용조회(@PathVariable String id){
//      상대가 안읽으면 표시가 되어있고, 읽으면 표시가 사라지게(본인도 마찬가지)

    }

    @PostMapping("chat/{nickname}")
    public Chat 메세지전송(@PathVariable String nickname, String contents){
//
        return this.chatService.sendMessage(nickname, contents);
    }

    @PutMapping("chat/{id}")
    public void 메세지삭제(){
//      전송시간으로 부터 1분이내에만 삭제 가능 => "삭제된 메세지입니다." 로 수정됨
    }
}
