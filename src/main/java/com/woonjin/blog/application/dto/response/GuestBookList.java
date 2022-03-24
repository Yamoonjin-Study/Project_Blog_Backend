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

    private int userId;

    private String comment;

    private Timestamp date;

    private String userNickName;

    private String blogIcon;

    public static GuestBookList of(int id, int userId, String comment, Timestamp date,
        String userNickName, String blogIcon) {
        return new GuestBookList(id, userId, comment, date, userNickName, blogIcon);
    }
}
