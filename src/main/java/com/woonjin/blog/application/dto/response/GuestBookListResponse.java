package com.woonjin.blog.application.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuestBookListResponse {

    private String responseMessage;

    private String blogName;

    private List<GuestBookList> guestBookList;

    public static GuestBookListResponse of(String responseMessage, String blogName, List<GuestBookList> guestBookList){
        return new GuestBookListResponse(responseMessage, blogName, guestBookList);
    }
}
