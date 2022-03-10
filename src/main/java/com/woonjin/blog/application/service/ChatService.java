package com.woonjin.blog.application.service;

import com.woonjin.blog.application.dto.response.CancelMessageResponse;
import com.woonjin.blog.application.dto.response.ChatListResponse;
import com.woonjin.blog.application.dto.response.ChatRoomResponse;
import com.woonjin.blog.domain.entity.ChatMessage;
import com.woonjin.blog.domain.entity.ChatRoom;
import com.woonjin.blog.domain.entity.User;
import com.woonjin.blog.domain.repository.ChatMessageRepository;
import com.woonjin.blog.domain.repository.ChatRoomRepository;
import com.woonjin.blog.domain.repository.UserRepository;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final IdentityAppService identityAppService;
    private final static Logger Log = Logger.getGlobal();

    public ChatService(
        ChatMessageRepository chatMessageRepository,
        ChatRoomRepository chatRoomRepository,
        UserRepository userRepository,
        IdentityAppService identityAppService
    ) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
        this.identityAppService = identityAppService;
    }

    //안읽은 메세지가 있는 채팅방부터 마지막 메세지가 온 시간 순으로 정렬되어야 함.
    public List<ChatListResponse> showChatList() {
        User user = this.identityAppService.getAuthenticationUser(); //유저
        List<ChatRoom> chatRooms = this.chatRoomRepository.findByChatUsersOrderByLastChatDateDesc(
            user); //유저의 채팅방들 시간순 정렬
        List<ChatListResponse> chatRoomInfos = new ArrayList<>();

        for (int i = 0; i < chatRooms.size(); i++) {

            ChatMessage lastChatMessage =
                this.chatMessageRepository.findFirstByChatRoomOrderBySendDateDesc(chatRooms.get(i));
            Boolean chatReadingCheck;

            if (lastChatMessage.getReaders().contains(user)) {
                chatReadingCheck = true;
            } else {
                chatReadingCheck = false;
            }

            ChatListResponse chatRoomInfo = ChatListResponse.of(
                chatRooms.get(i), //가장 최근까지 채팅 했었던 채팅방
                lastChatMessage, //해당 채팅방의 가장 마지막 채팅
                chatReadingCheck //해당 채팅의 읽음 여부
            );

            chatRoomInfos.add(chatRoomInfo);
        }

        return chatRoomInfos;
    }

    public ChatRoomResponse showChatRoom(int id) {
        User user = this.identityAppService.getAuthenticationUser();
        ChatRoom chatRoom = this.chatRoomRepository.findById(id);
        List<ChatMessage> chatMessages = this.chatMessageRepository.findByChatRoomOrderBySendDateAsc(
            chatRoom);

        // 해당 채팅방 모두 읽음처리.
        for (int i = 0; i < chatMessages.size(); i++) {

            ChatMessage chatMessage = chatMessages.get(i);

            List<User> chatReaders = chatMessage.getReaders();
            chatReaders.add(user);

            chatMessage.setReaders(chatReaders);

            this.chatMessageRepository.save(chatMessage);
        }

        return ChatRoomResponse.of("", chatRoom, chatMessages);
    }

    public ChatMessage sendMessage(String message, ChatRoom chatRoom) {
        User sender = this.identityAppService.getAuthenticationUser();

        List<User> readers = new ArrayList<>();
        readers.add(sender);

        try {
            ChatMessage sendMessage = this.chatMessageRepository.save(
                ChatMessage.of(
                    sender,
                    message,
                    chatRoom,
                    readers,
                    null
                )
            );

            this.Log.info("Send Message Success");
            return sendMessage;
        } catch (Exception e) {
            e.printStackTrace();

            this.Log.warning("Send Message Fail");
            return ChatMessage.of(
                sender,
                message,
                chatRoom,
                readers,
                null
            );
        }
    }

    public CancelMessageResponse cancelMessage(int id) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        ChatMessage cancelSendingMessage = this.chatMessageRepository.findById(id);

        if (now.getTime() - cancelSendingMessage.getSendDate().getTime() > 1000L * 60) {
            cancelSendingMessage.setMessage("삭제된 메세지 입니다.");
            this.chatMessageRepository.save(cancelSendingMessage);

            this.Log.info("Cancel Sending Message Success");
            return CancelMessageResponse.of("Cancel Sending Message Success", cancelSendingMessage);
        } else {
            this.Log.warning("Cancel Sending Message Fail");
            return CancelMessageResponse.of("Cancel Sending Message Fail", cancelSendingMessage);
        }
    }

}
