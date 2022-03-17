package com.woonjin.blog.domain.entity;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Table(name = "chat_rooms")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = false)
    private String chatRoomName;

    @CreationTimestamp
    private Timestamp lastChatMessage;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<User> chatUsers;

    @CreationTimestamp
    private Timestamp createDate;


    private ChatRoom(
        String chatRoomName,
        Timestamp lastChatMessage,
        List<User> chatUsers
    ) {
        this.chatRoomName = chatRoomName;
        this.lastChatMessage = lastChatMessage;
        this.chatUsers = chatUsers;
    }

    public static ChatRoom of(
        String chatRoomName,
        Timestamp lastChatMessage,
        List<User> chatUsers
    ) {
        return new ChatRoom(
            chatRoomName,
            lastChatMessage,
            chatUsers
        );
    }
}
