package com.woonjin.blog.domain.entity;

import java.sql.Timestamp;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
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

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(name = "nick_name", nullable = false, length = 30, unique = true)
    private String nick_name;

    @Column(nullable = true, length = 200)
    private String info;

    @Column(nullable = true, length = 200)
    private String icon;

    @CreationTimestamp
    private Timestamp create_date;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "logo_image", nullable = true, length = 200)
    private String logo_image;

    @Column(nullable = false)
    @ColumnDefault("1")
    private int design_form;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @OneToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_blog_id"), unique = true, nullable = false)
    private User user;


    public Blog(
        String name,
        String nick_name,
        String info,
        String icon,
        Status status,
        String logo_image,
        int design_form,
        Category category,
        User user
    ) {
        this.name = name;
        this.nick_name = nick_name;
        this.info = info;
        this.icon = icon;
        this.status = status;
        this.logo_image = logo_image;
        this.design_form = design_form;
        this.category = category;
        this.user = user;
    }

    public static Blog of(
        String name,
        String nick_name,
        String info,
        String icon,
        Status status,
        String logo_image,
        int design_form,
        Category category,
        User user
    ) {
        return new Blog(
            name,
            nick_name,
            info,
            icon,
            status,
            logo_image,
            design_form,
            category,
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

    @Getter
    public enum Category {
        BUSINESS("BUSINESS"),
        PERSONAL("PERSONAL");


        private final String name;

        Category(String name) {
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
