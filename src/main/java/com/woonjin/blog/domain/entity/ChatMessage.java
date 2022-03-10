package com.woonjin.blog.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name="fk_chat_room_chat_message_id"), nullable = false)
    private ChatRoom chatRoom;

    @Column
    private String message;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "sender", foreignKey = @ForeignKey(name = "fk_sender_chatMessage_id"), nullable = false)
    private User sender;


    @CreationTimestamp
    private Timestamp sendDate;

    @OneToMany
    private List<User> readers;

    @OneToMany
    private List<User> likeUsers;

    private ChatMessage(
        User sender,
        String message,
        ChatRoom chatRoom,
        List<User> readers,
        List<User> likeUsers
    ) {
        this.sender = sender;
        this.message = message;
        this.chatRoom = chatRoom;
        this.readers = readers;
        this.likeUsers = likeUsers;
    }

    public static ChatMessage of(
        User sender,
        String contents,
        ChatRoom chatRoom,
        List<User> readers,
        List<User> likeUsers
    ){
        return new ChatMessage(
            sender,
            contents,
            chatRoom,
            readers,
            likeUsers
        );
    }
}
