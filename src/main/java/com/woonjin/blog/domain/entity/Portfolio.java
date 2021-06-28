package com.woonjin.blog.domain.entity;

import com.woonjin.blog.domain.entity.Resume.Type;
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
@Table(name = "portfolios")
public class Portfolio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    private String content;

    @Column(nullable = false, length = 255)
    private String title;

    @CreationTimestamp
    private Timestamp createDate;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    @ColumnDefault("0")
    private int count;

    @OneToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_portfolio_id"), unique = true, nullable = false)
    private User user;

    @Getter
    public enum Type {
        PORTFOLIO("PORTFOLIO");

        private final String name;

        Type(String name) {
            this.name = name;
        }
    }

    public Portfolio(
        Portfolio.Type type,
        String content,
        String title,
        User user
    ) {
        this.type = type;
        this.content = content;
        this.title = title;
        this.user = user;
    }

    public static Portfolio of(
        Portfolio.Type type,
        String content,
        String title,
        User user
    ) {
        return new Portfolio(
            type,
            content,
            title,
            user
        );
    }
}
