package com.woonjin.blog.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {

    private String email;

    private String password;

    private String passwordCheck;

    private String nick_name;

    private String username;

    private String phone;
}
