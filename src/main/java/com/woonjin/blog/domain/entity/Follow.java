package com.woonjin.blog.domain.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    @OneToOne
    @JoinColumn(name = "following_blog_id", foreignKey = @ForeignKey(name = "fk_blog_following_blog_id"), unique = true)
    private Blog followingBlog;

    @OneToOne
    @JoinColumn(name = "follower_blog_id", foreignKey = @ForeignKey(name = "fk_blog_follower_blog_id"), unique = true)
    private Blog followerBlog;

    @Column(name = "following_date")
    @CreationTimestamp
    private Timestamp followingDate;
}
