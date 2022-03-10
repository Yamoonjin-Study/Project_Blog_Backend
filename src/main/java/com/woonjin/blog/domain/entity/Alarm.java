package com.woonjin.blog.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alarms")
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String message;

    @Column
    private String url;

    @JoinColumn(name = "sender_id", foreignKey = @ForeignKey(name = "fk_user_sender_alarm_id"))
    @ManyToOne(fetch = FetchType.EAGER)
    private User sender;

    @JoinColumn(name = "receiver_id", foreignKey = @ForeignKey(name = "fk_user_receiver_alarm_id"))
    @ManyToOne(fetch = FetchType.EAGER)
    private User receiver;

    @Enumerated(value = EnumType.STRING)
    private MessageType messageType;

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'UnRead'")
    private Status status;

    @CreationTimestamp
    private Timestamp alarmDate;

    private Alarm(
        String message,
        String url,
        User sender,
        User receiver,
        MessageType messageType,
        Status status
    ) {
        this.message = message;
        this.url = url;
        this.sender = sender;
        this.receiver = receiver;
        this.messageType = messageType;
        this.status = status;
    }

    public static Alarm of(
        String message,
        String url,
        User sender,
        User receiver,
        MessageType messageType,
        Status status
    ) {
        return new Alarm(
            message,
            url,
            sender,
            receiver,
            messageType,
            status
        );
    }

    @Getter
    public enum MessageType {
        LikeBoard("LikeBoard"),
        WriteReply("WriteReply"),
        LikeReply("LikeReply"),
        OnChatMessage("OnChatMessage"),
        LikeChatMessage("LikeChatMessage");

        private final String name;

        MessageType(String name) {
            this.name = name;
        }
    }

    @Getter
    public enum Status {
        Read("Read"),
        UnRead("UnRead");

        private final String name;

        Status(String name) {
            this.name = name;
        }
    }
}
