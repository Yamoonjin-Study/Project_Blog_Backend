package com.woonjin.blog.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogInRequest {

  private String email;

  private String password;
}
