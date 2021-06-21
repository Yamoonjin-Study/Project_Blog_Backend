package com.woonjin.blog.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(length = 500)
    private String image;

    @CreationTimestamp
    private Timestamp createDate;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    @Column(columnDefinition = "integer default 0")
    private int count;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_profile_id"), unique = false, nullable = false)
    private User user;

    @Getter
    public enum Type {
        RESUME("RESUME"),
        PROJECT("PROJECT");

        private final String name;

        Type(String name) {
            this.name = name;
        }
    }

}
