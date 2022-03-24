package com.woonjin.blog.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.sql.Timestamp;

import java.util.List;


import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

    @Column(name = "blog_name", nullable = false, length = 30, unique = true)
    private String blogName;

    @Column(length = 200)
    private String info;

    @Column(name = "icon_image", length = 500)
    private String iconImage;

    @CreationTimestamp
    @Column(name = "create_date")
    private Timestamp createDate;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "logo_image", length = 500)
    private String logoImage;

    @Column(name = "main_content", nullable = false)
    @ColumnDefault("1")
    private int mainContent;

    @Column(name = "menu_design", nullable = false)
    @ColumnDefault("1")
    private int menuDesign;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_blog_id"), unique = true, nullable = false)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "blog", cascade = {
        CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Visitor> visitor;

    @JsonIgnore
    @OneToMany(mappedBy = "blog", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<GuestBook> guestBook;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "business_card_id", foreignKey = @ForeignKey(name = "fk_business_card_blog_id"), unique = true)
    private Archive businessCard;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "portfolio_id", foreignKey = @ForeignKey(name = "fk_portfolio_blog_id"), unique = true)
    private Archive portfolio;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "resume_id", foreignKey = @ForeignKey(name = "fk_resume_blog_id"), unique = true)
    private Archive resume;


    private Blog(
        String blogName,
        String info,
        String iconImage,
        Status status,
        String logoImage,
        int mainContent,
        int menuDesign,
        Category category,
        User user,
        Archive businessCard,
        Archive portfolio,
        Archive resume
    ) {
        this.blogName = blogName;
        this.info = info;
        this.iconImage = iconImage;
        this.status = status;
        this.logoImage = logoImage;
        this.mainContent = mainContent;
        this.menuDesign = menuDesign;
        this.category = category;
        this.user = user;
        this.businessCard = businessCard;
        this.portfolio = portfolio;
        this.resume = resume;
    }

    public static Blog of(
        String blogName,
        String info,
        String iconImage,
        Status status,
        String logoImage,
        int mainContent,
        int menuDesign,
        Category category,
        User user,
        Archive businessCard,
        Archive portfolio,
        Archive resume
    ) {
        return new Blog(
            blogName,
            info,
            iconImage,
            status,
            logoImage,
            mainContent,
            menuDesign,
            category,
            user,
            businessCard,
            portfolio,
            resume
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
