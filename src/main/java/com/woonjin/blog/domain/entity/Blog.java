package com.woonjin.blog.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @JsonIgnore
    @OneToMany(mappedBy = "blog", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Visitor> visitor;

    @JsonIgnore
    @OneToMany(mappedBy = "blog", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<GuestBook> guestBook;


    public Blog(
        String name,
        String info,
        String icon,
        Status status,
        String logo_image,
        int design_form,
        Category category,
        User user
    ) {
        this.name = name;
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
