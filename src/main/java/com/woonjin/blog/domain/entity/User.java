package com.woonjin.blog.domain.entity;

import java.sql.Timestamp;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(name = "nick_name", nullable = false, length = 30, unique = true)
    private String nick_name;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 11)
    private String phone;

    @Enumerated(value = EnumType.STRING)
    private RoleType role;

    @CreationTimestamp
    private Timestamp createDate;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private User(
        String email,
        String password,
        String username,
        String nick_name,
        String phone,
        Status status,
        RoleType role
    ) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.nick_name = nick_name;
        this.phone = phone;
        this.status = status;
        this.role = role;
    }

    public static User of(
        String email,
        String password,
        String username,
        String nick_name,
        String phone,
        Status status,
        RoleType role
    ) {
        return new User(
            email,
            password,
            username,
            nick_name,
            phone,
            status,
            role
        );
    }

    @Getter
    public enum RoleType {
        USER("USER"),
        ADMIN("ADMIN");

        private final String name;

        RoleType(String name) {
            this.name = name;
        }
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

    public void inactivate(User user) {
        user.setStatus(Status.INACTIVE);
    }

    public void activate(User user) {
        user.setStatus(Status.ACTIVE);
    }
}

