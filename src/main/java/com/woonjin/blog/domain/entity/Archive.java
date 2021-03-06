package com.woonjin.blog.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "archives")
public class Archive implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    private String content;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = true, length = 500)
    private String filePath;

    @CreationTimestamp
    @Column(name = "create_date")
    private Timestamp createDate;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    @ColumnDefault("0")
    private int count;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "blog_id", foreignKey = @ForeignKey(name = "fk_blog_archive_id"))
    private Blog blog;

    private Archive(
        Archive.Type type,
        String content,
        String title,
        String filePath,
        Blog blog
    ) {
        this.type = type;
        this.content = content;
        this.title = title;
        this.filePath = filePath;
        this.blog = blog;
    }

    public static Archive of(
        Archive.Type type,
        String content,
        String title,
        String filePath,
        Blog blog
    ) {
        return new Archive(
            type,
            content,
            title,
            filePath,
            blog
        );
    }

    @Getter
    public enum Type {
        PORTFOLIO("PORTFOLIO"),
        RESUME("RESUME"),
        BUSINESS_CARD("BUSINESS_CARD");

        private final String name;

        Type(String name) {
            this.name = name;
        }
    }
}
