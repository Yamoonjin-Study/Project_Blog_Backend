package com.woonjin.blog.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInRequest {

    private String username;

    private String password;
}