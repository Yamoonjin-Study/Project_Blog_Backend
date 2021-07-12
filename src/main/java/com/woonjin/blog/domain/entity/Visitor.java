package com.woonjin.blog.domain.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Entity
@Table(name = "blog_visitors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blogs_id", foreignKey = @ForeignKey(name = "fk_blog_visitor_id"), nullable = false)
    private Blog blog;

    @OneToOne
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "fk_user_visitor_id"))
    private User user;

    @CreationTimestamp
    private Timestamp date;
}
