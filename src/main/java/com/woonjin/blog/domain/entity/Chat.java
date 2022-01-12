package com.woonjin.blog.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "sender", foreignKey = @ForeignKey(name = "fk_user1_chat_id"), nullable = false)
    private User sender;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver", foreignKey = @ForeignKey(name = "fk_user2_chat_id"), nullable = false)
    private User receiver;

    @Column
    private String contents;

    @CreationTimestamp
    private Timestamp date;

    @Enumerated(EnumType.STRING)
    private Status statuses;

    private Chat(
        User sender,
        User receiver,
        String contents,
        Status statuses
    ) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.contents = contents;
        this.statuses = statuses;
    }

    public static Chat of(
        User sender,
        User receiver,
        String contents,
        Status statuses
    ){
        return new Chat(
            sender,
            receiver,
            contents,
            statuses
        );
    }

    @Getter
    public enum Status {
        READ("READ"),
        UNREAD("UNREAD");

        private final String name;

        Status(String name) {
            this.name = name;
        }
    }
}
