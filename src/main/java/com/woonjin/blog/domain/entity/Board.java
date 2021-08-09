package com.woonjin.blog.domain.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 30, unique = true)
    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_board_id"))
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

    @ColumnDefault("0")
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_category_board_id"))
    private Category category;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Like> likes;

    @OneToMany(mappedBy = "top_board")
    private List<Reply> replies;

    private Board(
        String title,
        String content,
        Category category
    ){
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public static Board of(
        String title,
        String content,
        Category category
    ){
        return new Board(
            title,
            content,
            category
        );
    }
}
