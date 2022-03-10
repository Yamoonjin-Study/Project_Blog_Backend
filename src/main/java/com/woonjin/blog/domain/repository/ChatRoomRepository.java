package com.woonjin.blog.domain.repository;

import com.woonjin.blog.domain.entity.ChatRoom;
import com.woonjin.blog.domain.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {

    ChatRoom findById(int id);

    List<ChatRoom> findByChatUsersOrderByLastChatDateDesc(User user);
}
