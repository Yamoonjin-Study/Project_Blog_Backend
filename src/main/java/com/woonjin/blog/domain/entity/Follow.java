package com.woonjin.blog.domain.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "follows")
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "following_blog_id", foreignKey = @ForeignKey(name = "fk_blog_following_blog_id"), unique = false)
    private Blog followingBlog;

    @ManyToOne
    @JoinColumn(name = "blogger", foreignKey = @ForeignKey(name = "fk_blog_blogger_id"), unique = false)
    private Blog blogger;

    @Column(name = "following_date")
    @CreationTimestamp
    private Timestamp followingDate;

    private Follow(
        Blog followingBlog,
        Blog blogger
    ) {
        this.followingBlog = followingBlog;
        this.blogger = blogger;
    }

    public static Follow of(
        Blog followingBlog,
        Blog blogger
    ) {
        return new Follow(
            followingBlog,
            blogger
        );
    }
}
