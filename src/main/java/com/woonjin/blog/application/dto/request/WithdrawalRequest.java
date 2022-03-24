package com.woonjin.blog.application.dto.request;

import lombok.Getter;

@Getter
public class WithdrawalRequest {

    private String email;

    private String password;

    private String reason;
}
