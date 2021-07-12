package com.woonjin.blog.domain.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "resumes")
public class Resume implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    private String content;

    @Column(nullable = false, length = 255)
    private String title;

    @CreationTimestamp
    private Timestamp createDate;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    @ColumnDefault("0")
    private int count;

    @OneToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_resume_id"), unique = true, nullable = false)
    private User user;

    private Resume(
        Type type,
        String content,
        String title,
        User user
    ) {
        this.type = type;
        this.content = content;
        this.title = title;
        this.user = user;
    }

    public static Resume of(
        Type type,
        String content,
        String title,
        User user
    ) {
        return new Resume(
            type,
            content,
            title,
            user
        );
    }

    @Getter
    public enum Type {
        RESUME("RESUME");

        private final String name;

        Type(String name) {
            this.name = name;
        }
    }
}
