package com.woonjin.blog.domain.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "guest_book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GuestBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id", foreignKey = @ForeignKey(name = "fk_blog_guest_book_id"), nullable = false)
    private Blog blog;

    @Column(nullable = false, length = 500)
    private String comment;

    @CreationTimestamp
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_guest_book_id"), nullable = false)
    private User user;

    private GuestBook(
        Blog blog,
        String comment,
        User user
    ) {
        this.blog = blog;
        this.comment = comment;
        this.user = user;
    }

    public static GuestBook of(
        Blog blog,
        String comment,
        User user
    ) {
        return new GuestBook(
            blog,
            comment,
            user
        );
    }
}
