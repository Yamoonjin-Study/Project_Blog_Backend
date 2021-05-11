package com.woonjin.blog.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

  private String email;

  private String password;

  private String passwordCheck;

  private String username;

  private String phone;
}
