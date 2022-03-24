package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.GuestBook;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WriteGuestBookResponse {

    private String responseMessage;

    private GuestBook guestBook;

    public static WriteGuestBookResponse of(String responseMessage, GuestBook guestBook){
        return new WriteGuestBookResponse(responseMessage, guestBook);
    }
}
