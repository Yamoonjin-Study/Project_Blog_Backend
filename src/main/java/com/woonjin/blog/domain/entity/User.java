package com.woonjin.blog.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(name = "nickname", nullable = false, length = 30, unique = true)
    private String nickname;

    @Column(nullable = false, length = 50, unique = true)
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
        this.nickname = nick_name;
        this.phone = phone;
        this.status = status;
        this.role = role;
    }

    public static User of(
        String email,
        String password,
        String username,
        String nickname,
        String phone,
        Status status,
        RoleType role
    ) {
        return new User(
            email,
            password,
            username,
            nickname,
            phone,
            status,
            role
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();

        collectors.add(() -> "ROLE_"+this.getRole());

        return collectors;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
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

