package com.woonjin.blog.application.dto.request;

import lombok.Getter;

@Getter
public class SignUpRequest {

    private String email;

    private String password;

    private String passwordCheck;

    private String nickname;

    private String username;

    private String phone;
}
