package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.User;
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
    private String comment;

    private Timestamp date;

    private String user_nickname;

    public static GuestBookList of(String comment, Timestamp date, String user_nickname){
        return new GuestBookList(comment, date, user_nickname);
    }
}
