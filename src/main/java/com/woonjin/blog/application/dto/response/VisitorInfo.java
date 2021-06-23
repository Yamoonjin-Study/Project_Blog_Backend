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
public class VisitorInfo {
    private String user_nickname;

    private Timestamp date;

    public static VisitorInfo of(String user_nickname, Timestamp date){
        return new VisitorInfo(user_nickname, date);
    }
}
