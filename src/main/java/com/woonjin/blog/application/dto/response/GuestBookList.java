package com.woonjin.blog.application.dto.response;

import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuestBookList {

    private int id;

    private int user_id;

    private String comment;

    private Timestamp date;

    private String user_nickname;

    private String blog_icon;

    public static GuestBookList of(int id, int user_id, String comment, Timestamp date,
        String user_nickname, String blog_icon) {
        return new GuestBookList(id, user_id, comment, date, user_nickname, blog_icon);
    }
}
