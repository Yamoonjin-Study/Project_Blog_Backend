package com.woonjin.blog.application.dto.response;

import com.woonjin.blog.domain.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WithdrawalResponse {

    private String responseMessage;

    private User user;

    public static WithdrawalResponse of(String responseMessage, User user) {
        return new WithdrawalResponse(responseMessage, user);
    }
}
