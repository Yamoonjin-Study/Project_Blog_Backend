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
    private String userNickName;

    private Timestamp date;

    public static VisitorInfo of(String userNickName, Timestamp date){
        return new VisitorInfo(userNickName, date);
    }
}
