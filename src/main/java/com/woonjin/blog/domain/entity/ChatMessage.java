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
import javax.persistence.ManyToMany;
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
    @JoinColumn(name="chat_rooms_id", foreignKey = @ForeignKey(name="fk_chat_room_chat_message_id"), nullable = false)
    private ChatRoom chatRoom;

    @Column(length = 1000)
    private String message;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "sender", foreignKey = @ForeignKey(name = "fk_sender_chatMessage_id"), nullable = false)
    private User sender;

    @CreationTimestamp
    private Timestamp sendDate;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> readers;

    private ChatMessage(
        User sender,
        String message,
        ChatRoom chatRoom,
        List<User> readers
    ) {
        this.sender = sender;
        this.message = message;
        this.chatRoom = chatRoom;
        this.readers = readers;
    }

    public static ChatMessage of(
        User sender,
        String contents,
        ChatRoom chatRoom,
        List<User> readers
    ){
        return new ChatMessage(
            sender,
            contents,
            chatRoom,
            readers
        );
    }
}
