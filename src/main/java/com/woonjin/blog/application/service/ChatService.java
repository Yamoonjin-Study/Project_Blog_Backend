package com.woonjin.blog.application.service;

import com.woonjin.blog.domain.entity.Chat;
import com.woonjin.blog.domain.entity.Chat.Status;
import com.woonjin.blog.domain.entity.User;
import com.woonjin.blog.domain.repository.ChatRepository;
import com.woonjin.blog.domain.repository.UserRepository;
import java.sql.Timestamp;
import java.util.logging.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final static Logger Log = Logger.getGlobal();

    public ChatService(
        ChatRepository chatRepository,
        UserRepository userRepository
    ) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public void readMessage(Chat chat) {

        chat.setStatuses(Status.READ);

    }

    public Chat sendMessage(String receivername, String contents) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User sender = this.userRepository.findByEmail(email);

        User receiver = this.userRepository.findByNickname(receivername);
        try {
            Chat sendMessage = this.chatRepository.save(
                Chat.of(
                    sender,
                    receiver,
                    contents,
                    Status.UNREAD
                )
            );
            this.Log.info("Send Message Success");
            return sendMessage;
        } catch (Exception e) {
            e.printStackTrace();
            this.Log.warning("Send Message Fail");
            return Chat.of(
                sender,
                receiver,
                contents,
                Status.UNREAD
            );
        }
    }

    public void cancelMessage(Chat chat) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        if(now.getTime() - chat.getDate().getTime() > 1000L * 60){
            Chat cancelSendingMessage = this.chatRepository.save(
                Chat.of(
                    chat.getSender(),
                    chat.getReceiver(),
                    "삭제된 메세지 입니다.",
                    chat.getStatuses()
                )
            );
            this.Log.info("Cancel Sending Message Success");
        }else {
            this.Log.warning("Cancel Sending Message Fail");
        }
    }

}
