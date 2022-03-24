package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.ChatMessage;
import com.woonjin.blog.domain.entity.ChatRoom;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {

    List<ChatMessage> findByChatRoomOrderBySendDateAsc(ChatRoom chatRoom);

    ChatMessage findFirstByChatRoomOrderBySendDateDesc(ChatRoom chatRoom);

    ChatMessage findById(int id);
}
