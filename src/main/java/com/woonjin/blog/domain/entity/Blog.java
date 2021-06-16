package com.woonjin.blog.domain.entity;

import java.sql.Timestamp;

import javax.persistence.*;


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
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true, length = 30, unique = true)
    private String blogname;

    @Column(nullable = true, length = 30, unique = true)
    private String nickname;

    @Column(nullable = true, length = 100)
    private String info;

    @Column(nullable = true, length = 100)
    private String icon;

    @CreationTimestamp
    private Timestamp createDate;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_blog_id"), unique = true, nullable = false)
    private User user;


    public Blog(
        String blogname,
        String nickname,
        String info,
        String icon,
        Status status,
        User user
    ) {
        this.blogname = blogname;
        this.nickname = nickname;
        this.info = info;
        this.icon = icon;
        this.status = status;
        this.user = user;
    }

    public static Blog of(
        String blogname,
        String nickname,
        String info,
        String icon,
        Status status,
        User user
    ) {
        return new Blog(
            blogname,
            nickname,
            info,
            icon,
            status,
            user
        );
    }

    @Getter
    public enum Status {
        ACTIVE("ACTIVE"),
        INACTIVE("INACTIVE");

        private final String name;

        Status(String name) {
            this.name = name;
        }
    }

    public void inactivate(Blog blog) {
        blog.setStatus(Status.INACTIVE);
    }

    public void activate(Blog blog) {
        blog.setStatus(Status.ACTIVE);
    }
}
