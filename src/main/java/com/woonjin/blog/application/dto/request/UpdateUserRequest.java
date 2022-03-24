package com.woonjin.blog.application.dto.request;

import lombok.Getter;

@Getter
public class UpdateUserRequest {

    private String email;

    private String existingPassword;

    private String password;

    private String passwordCheck;

    private String username;

    private String phone;
}
